package com.burixer85.aniclips.data.response.search

import com.burixer85.aniclips.data.response.search.dto.ThumbnailDto
import com.burixer85.aniclips.data.response.search.dto.toDomain
import com.burixer85.aniclips.domain.model.main.search.GetAllThumbnails

data class GetAllThumbnailsResponse(
    val content: List<ThumbnailDto>
)

fun GetAllThumbnailsResponse.toDomain(): GetAllThumbnails {
    return GetAllThumbnails(
        thumbnails = content.map { it.toDomain() }
    )
}
