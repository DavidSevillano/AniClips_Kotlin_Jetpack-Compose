package com.burixer85.aniclips.data.network.auth

import com.burixer85.aniclips.data.request.ActivateAccountRequest
import com.burixer85.aniclips.data.response.auth.ActivateAccountResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ActivateAccountClient {
    @POST("activate/account")
    suspend fun activateAccount(@Body activateAccountRequest: ActivateAccountRequest): Response<ActivateAccountResponse>
}