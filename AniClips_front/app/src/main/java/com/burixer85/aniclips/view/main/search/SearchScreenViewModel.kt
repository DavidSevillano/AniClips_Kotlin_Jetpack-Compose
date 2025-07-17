package com.burixer85.aniclips.view.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.domain.model.main.search.Thumbnail
import com.burixer85.aniclips.domain.repository.SearchRepository
import com.burixer85.aniclips.view.main.home.HomeUiState
import com.burixer85.aniclips.view.main.model.ThumbnailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _thumbnails = MutableStateFlow<List<ThumbnailUi>>(emptyList())
    val thumbnails: StateFlow<List<ThumbnailUi>> = _thumbnails

    fun setLoadingTrue() {
        _uiState.update { it.copy(isLoading = true) }
    }

    fun setLoadingFalse() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun loadThumbnails(page: Int = 0, size: Int = 18) {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val result = searchRepository.getAllThumbnails(page, size)
            val elapsed = System.currentTimeMillis() - startTime

            val thumbnailsDomain = result?.thumbnails ?: emptyList()
            val thumbnailsUi = thumbnailsDomain.map { mapThumbnailToUi(it) }
            _thumbnails.value = thumbnailsUi

            if (elapsed < 100) {
                setLoadingFalse()
            } else {
                setLoadingTrue()
                delay(100)
                setLoadingFalse()
            }
        }
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
    val isLoading: Boolean = false
)