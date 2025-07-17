package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.response.search.GetAllThumbnailsResponse
import com.burixer85.aniclips.data.response.search.toDomain
import com.burixer85.aniclips.data.service.search.ThumbnailService
import com.burixer85.aniclips.domain.model.main.search.GetAllThumbnails
import com.burixer85.aniclips.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val thumbnailService: ThumbnailService) :
    SearchRepository {

    override suspend fun getAllThumbnails(page: Int, size: Int): GetAllThumbnails? {
        val getAllThumbnailsResponse: GetAllThumbnailsResponse? =
            thumbnailService.getAllThumbnails(page, size)

        return getAllThumbnailsResponse?.toDomain()

    }
}