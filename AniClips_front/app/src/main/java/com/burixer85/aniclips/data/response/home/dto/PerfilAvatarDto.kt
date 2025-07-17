package com.burixer85.aniclips.data.response.home.dto

import com.burixer85.aniclips.domain.model.main.home.AvatarProfile

data class PerfilAvatarDto(
    val avatar: String
)

fun PerfilAvatarDto.toDomain(): AvatarProfile {
    return AvatarProfile(
        avatarUrl = avatar
    )
}