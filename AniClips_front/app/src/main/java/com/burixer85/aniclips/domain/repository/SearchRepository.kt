package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.main.search.GetAllFilteredThumbnails
import com.burixer85.aniclips.domain.model.main.search.GetAllThumbnails

interface SearchRepository {
    suspend fun getAllThumbnails(page: Int, size: Int): GetAllThumbnails?

    suspend fun getAllFilteredThumbnails(
        search: String,
        page: Int,
        size: Int
    ): GetAllFilteredThumbnails?
}