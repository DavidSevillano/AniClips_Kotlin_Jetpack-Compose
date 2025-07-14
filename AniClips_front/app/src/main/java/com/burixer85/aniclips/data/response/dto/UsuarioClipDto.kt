package com.burixer85.aniclips.data.response.dto

import com.burixer85.aniclips.domain.model.main.home.UserClip

data class UsuarioClipDto(
    val idUser: String,
    val username: String,
    val getPerfilAvatarDto: PerfilAvatarDto
)

fun UsuarioClipDto.toDomain(): UserClip {
    return UserClip(
        id = idUser,
        username = username,
        avatar = getPerfilAvatarDto.toDomain()
    )
}