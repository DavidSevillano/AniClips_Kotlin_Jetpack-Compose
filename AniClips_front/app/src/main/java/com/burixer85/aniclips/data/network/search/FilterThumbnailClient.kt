package com.burixer85.aniclips.data.network.search

import com.burixer85.aniclips.data.response.search.GetAllFilteredThumbailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterThumbnailClient {
    @GET("/clip/buscar/")
    suspend fun getAllFilteredThumbnails(
        @Query("search") search: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<GetAllFilteredThumbailsResponse>
}