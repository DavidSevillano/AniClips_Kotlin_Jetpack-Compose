package com.burixer85.aniclips.data.network.auth

import com.burixer85.aniclips.data.request.LoginRequest
import com.burixer85.aniclips.data.response.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}