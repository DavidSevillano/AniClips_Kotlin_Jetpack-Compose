package com.burixer85.aniclips.view.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.main.profile.Profile
import com.burixer85.aniclips.domain.model.main.search.Thumbnail
import com.burixer85.aniclips.domain.repository.ProfileRepository
import com.burixer85.aniclips.view.main.model.ProfileUi
import com.burixer85.aniclips.view.main.model.ThumbnailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    val profileRepository: ProfileRepository,
    val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    private val _profileData = MutableStateFlow<ProfileUi>(ProfileUi())
    val profileData: StateFlow<ProfileUi> = _profileData

    fun loadProfile() {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            val profile = profileRepository.getMyProfile("Bearer $token")
            android.util.Log.d("ProfileDebug", "Perfil recibido: $profile")
            profile?.let {
                _profileData.value = mapProfileToUi(it)
            }
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

fun mapProfileToUi(profile: Profile): ProfileUi {
    return ProfileUi(
        username = profile.username,
        avatar = profile.foto,
        clipsCount = profile.numeroClips,
        followers = profile.numeroSeguidores,
        followed = profile.numeroSeguidos,
        description = profile.descripcion,
        clips = profile.clips.map { mapThumbnailToUi(it) }
    )
}

data class ProfileUiState(
    val username: String = ""
)