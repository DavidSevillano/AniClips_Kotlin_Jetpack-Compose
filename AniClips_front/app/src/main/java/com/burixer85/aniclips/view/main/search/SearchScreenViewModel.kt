package com.burixer85.aniclips.view.main.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.domain.model.main.search.Thumbnail
import com.burixer85.aniclips.domain.repository.SearchRepository
import com.burixer85.aniclips.view.main.model.ThumbnailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _thumbnails = MutableStateFlow<List<ThumbnailUi>>(emptyList())
    val thumbnails: StateFlow<List<ThumbnailUi>> = _thumbnails

    private var currentPage = 0


    fun setLoadingTrue() {
        _uiState.update { it.copy(isLoading = true) }
    }

    fun setLoadingFalse() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onSearchChanged(search: String) {
        _uiState.update { state -> state.copy(search = search) }
        if (search.isNotBlank()) {
            val query = "nombreAnime:$search"
            loadFilteredThumbnails(query)
        } else {
            currentPage = 0
            loadThumbnails(page = 0)
        }
    }

    fun loadThumbnails(page: Int = currentPage, size: Int = 21) {
        viewModelScope.launch {
            setLoadingTrue()
            val result = searchRepository.getAllThumbnails(page, size)
            val thumbnailsDomain = result?.thumbnails ?: emptyList()
            val thumbnailsUi = thumbnailsDomain.map { mapThumbnailToUi(it) }

            if (page == 0) {
                _thumbnails.value = thumbnailsUi
            } else {
                _thumbnails.value = _thumbnails.value + thumbnailsUi
            }
            currentPage = page
            setLoadingFalse()
        }
    }

    fun loadFilteredThumbnails(search: String, page: Int = 0, size: Int = 21) {
        viewModelScope.launch {
            setLoadingTrue()
            val result = searchRepository.getAllFilteredThumbnails(search, page, size)
            Log.d("ThumbnailsDebug", "Resultado: $result")
            val thumbnailsDomain = result?.thumbnails ?: emptyList()
            val thumbnailsUi = thumbnailsDomain.map { mapThumbnailToUi(it) }

            if (page == 0) {
                _thumbnails.value = thumbnailsUi
            } else {
                _thumbnails.value = _thumbnails.value + thumbnailsUi
            }
            currentPage = page
            setLoadingFalse()
        }
    }

    fun loadNextPage(size: Int = 21) {
        if (uiState.value.isPaging || uiState.value.isLoading) return
        _uiState.update { it.copy(isPaging = true) }
        val search = uiState.value.search
        if (search.isNotBlank()) {
            val query = "nombreAnime:$search"
            loadFilteredThumbnails(query, page = currentPage + 1, size = size)
        } else {
            loadThumbnails(page = currentPage + 1, size = size)
        }
        _uiState.update { it.copy(isPaging = false) }
    }

    fun mapThumbnailToUi(thumbnail: Thumbnail): ThumbnailUi {
        return ThumbnailUi(
            id = thumbnail.id,
            thumbnail = thumbnail.thumbnailUrl,
            animeName = thumbnail.animeName

        )
    }
}


data class SearchUiState(
    val search: String = "",
    val isLoading: Boolean = false,
    val isPaging: Boolean = false
)