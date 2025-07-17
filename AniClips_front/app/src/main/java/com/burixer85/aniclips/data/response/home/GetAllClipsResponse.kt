package com.burixer85.aniclips.data.response.home

import com.burixer85.aniclips.data.response.home.dto.ClipDto
import com.burixer85.aniclips.data.response.home.dto.toDomain
import com.burixer85.aniclips.domain.model.main.home.GetAllClips

data class GetAllClipsResponse(
    val content: List<ClipDto>
)

fun GetAllClipsResponse.toDomain(): GetAllClips {
    return GetAllClips(
        clips = content.map { it.toDomain() }
    )
}


