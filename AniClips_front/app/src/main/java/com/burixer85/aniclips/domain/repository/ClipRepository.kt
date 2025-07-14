package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.main.home.GetAllClips

interface ClipRepository {
    suspend fun getAllClips(page: Int, size: Int, token: String): GetAllClips?
}