package com.burixer85.aniclips.data.response.search

import com.burixer85.aniclips.data.response.search.dto.ThumbnailDto
import com.burixer85.aniclips.data.response.search.dto.toDomain
import com.burixer85.aniclips.domain.model.main.search.GetAllFilteredThumbnails

data class GetAllFilteredThumbailsResponse(
    val content: List<ThumbnailDto>
)

fun GetAllFilteredThumbailsResponse.toDomain(): GetAllFilteredThumbnails {
    return GetAllFilteredThumbnails(
        thumbnails = content.map { it.toDomain() }
    )
}
