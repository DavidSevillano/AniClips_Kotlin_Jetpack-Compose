package com.burixer85.aniclips.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.main.home.Clip
import com.burixer85.aniclips.domain.repository.HomeRepository
import com.burixer85.aniclips.view.main.model.ClipUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _clips = MutableStateFlow<List<ClipUi>>(emptyList())
    val clips: StateFlow<List<ClipUi>> = _clips

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun setLoadingTrue() {
        _uiState.update { it.copy(isLoading = true) }
    }

    fun setLoadingFalse() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun loadClips(page: Int = 0, size: Int = 4) {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val token = sessionManager.getToken()
            val result = homeRepository.getAllClips(page, size, "Bearer $token")
            val elapsed = System.currentTimeMillis() - startTime

            val clipsDomain = result?.clips ?: emptyList()
            val clipsUi = clipsDomain.map { mapClipToUi(it) }
            _clips.value = clipsUi

            if (elapsed < 100) {
                setLoadingFalse()
            } else {
                setLoadingTrue()
                delay(100)
                setLoadingFalse()
            }
        }
    }

    fun mapClipToUi(clip: Clip): ClipUi {
        return ClipUi(
            id = clip.id.toString(),
            username = clip.user.username,
            avatar = clip.user.avatar.avatarUrl,
            video = clip.videoUrl,
            thumbnail = clip.thumbnailUrl,
            likes = clip.likes,
            comments = clip.comments,
            ratingsCount = clip.ratingsCount,
            description = clip.description,
            date = clip.date
        )
    }
}

data class HomeUiState(
    val isLoading: Boolean = false
)