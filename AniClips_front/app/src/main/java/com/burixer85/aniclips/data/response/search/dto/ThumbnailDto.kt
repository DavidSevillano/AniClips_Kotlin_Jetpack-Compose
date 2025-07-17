package com.burixer85.aniclips.data.response.search.dto

import com.burixer85.aniclips.domain.model.main.search.Thumbnail

data class ThumbnailDto(
    val id: Int,
    val duracion: Int,
    val miniatura: String,
    val nombreAnime: String
)

fun ThumbnailDto.toDomain(): Thumbnail {
    return Thumbnail(
        id = id,
        thumbnailUrl = miniatura,
        animeName = nombreAnime
    )
}