package com.burixer85.aniclips.data.service.search

import com.burixer85.aniclips.data.network.search.FilterThumbnailClient
import com.burixer85.aniclips.data.response.search.GetAllFilteredThumbailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilteredThumbnailService @Inject constructor(private val filterThumbnailClient: FilterThumbnailClient) {
    suspend fun getAllFilteredThumbnails(
        search: String,
        page: Int, size: Int
    ): GetAllFilteredThumbailsResponse? {
        return withContext(Dispatchers.IO) {
            val response = filterThumbnailClient.getAllFilteredThumbnails(search, page, size)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}