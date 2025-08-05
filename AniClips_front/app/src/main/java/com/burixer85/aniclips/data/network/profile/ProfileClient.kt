package com.burixer85.aniclips.data.network.profile

import com.burixer85.aniclips.data.response.profile.GetMyProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileClient {
    @GET("/perfil/")
    suspend fun getMyProfile(
        @Header("Authorization") token: String
    ): Response<GetMyProfileResponse>
}