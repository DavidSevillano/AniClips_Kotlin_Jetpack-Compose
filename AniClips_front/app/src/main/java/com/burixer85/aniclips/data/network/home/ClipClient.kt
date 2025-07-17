package com.burixer85.aniclips.data.network.home

import com.burixer85.aniclips.data.response.home.GetAllClipsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ClipClient {
    @GET("/clip/")
    suspend fun getAllClips(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") token: String
    ): Response<GetAllClipsResponse>
}