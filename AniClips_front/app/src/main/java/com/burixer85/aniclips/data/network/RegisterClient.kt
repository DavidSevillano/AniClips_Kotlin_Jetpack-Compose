package com.burixer85.aniclips.data.network

import com.burixer85.aniclips.data.request.RegisterRequest
import com.burixer85.aniclips.data.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterClient {
    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}