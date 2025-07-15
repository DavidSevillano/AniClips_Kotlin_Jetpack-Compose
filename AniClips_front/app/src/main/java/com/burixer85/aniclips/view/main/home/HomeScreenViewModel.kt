package com.burixer85.aniclips.view.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.main.home.Clip
import com.burixer85.aniclips.domain.repository.ClipRepository
import com.burixer85.aniclips.view.main.model.ClipUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val clipRepository: ClipRepository,
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
            setLoadingTrue()
            val token = sessionManager.getToken()
            Log.d("HomeScreenViewModel", "Clips recibidos: $token")
            val result = clipRepository.getAllClips(page, size, "Bearer $token")
            Log.d("HomeScreenViewModel", "Clips recibidos: ${result?.clips}")
            val clipsDomain = result?.clips ?: emptyList()
            val clipsUi = clipsDomain.map { mapClipToUi(it) }
            _clips.value = clipsUi
            setLoadingFalse()
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