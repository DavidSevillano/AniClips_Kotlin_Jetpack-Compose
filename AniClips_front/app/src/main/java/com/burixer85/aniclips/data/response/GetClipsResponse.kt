package com.burixer85.aniclips.data.response

import com.burixer85.aniclips.data.response.dto.ClipDto
import com.burixer85.aniclips.data.response.dto.toDomain
import com.burixer85.aniclips.domain.model.main.home.GetAllClips

data class GetClipsResponse(
    val content: List<ClipDto>
)

fun GetClipsResponse.toDomain(): GetAllClips {
    return GetAllClips(
        clips = content.map { it.toDomain() }
    )
}


