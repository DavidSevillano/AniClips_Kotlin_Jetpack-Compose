package com.burixer85.aniclips.data.service.search

import com.burixer85.aniclips.data.network.search.ThumbnailClient
import com.burixer85.aniclips.data.response.search.GetAllThumbnailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThumbnailService @Inject constructor(private val thumbnailClient: ThumbnailClient) {

    suspend fun getAllThumbnails(page: Int, size: Int): GetAllThumbnailsResponse? {
        return withContext(Dispatchers.IO) {
            val response = thumbnailClient.getAllThumbnails(page, size)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}