package com.burixer85.aniclips.data.network.search

import com.burixer85.aniclips.data.response.search.GetAllThumbnailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ThumbnailClient {
    @GET("/clip/miniatura")
    suspend fun getAllThumbnails(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<GetAllThumbnailsResponse>
}