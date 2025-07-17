package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.main.search.GetAllThumbnails

interface SearchRepository {
    suspend fun getAllThumbnails(page: Int, size: Int): GetAllThumbnails?
}