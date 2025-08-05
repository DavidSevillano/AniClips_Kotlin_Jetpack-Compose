package com.burixer85.aniclips.data.response.profile

import com.burixer85.aniclips.data.response.search.dto.ThumbnailDto
import com.burixer85.aniclips.data.response.search.dto.toDomain
import com.burixer85.aniclips.domain.model.main.profile.Profile

data class GetMyProfileResponse(
    val username: String,
    val foto: String,
    val numeroClips: Int,
    val numeroSeguidores: Int,
    val numeroSeguidos: Int,
    val descripcion: String,
    val clips: List<ThumbnailDto>
)

fun GetMyProfileResponse.toDomain(): Profile {
    return Profile(
        username = username,
        foto = foto,
        numeroClips = numeroClips,
        numeroSeguidores = numeroSeguidores,
        numeroSeguidos = numeroSeguidos,
        descripcion = descripcion,
        clips = clips.map { it.toDomain() }
    )
}