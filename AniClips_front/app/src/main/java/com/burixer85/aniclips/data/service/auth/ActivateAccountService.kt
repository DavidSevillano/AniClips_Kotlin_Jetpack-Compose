package com.burixer85.aniclips.data.service.auth

import com.burixer85.aniclips.data.network.auth.ActivateAccountClient
import com.burixer85.aniclips.data.request.ActivateAccountRequest
import com.burixer85.aniclips.data.response.auth.ActivateAccountResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivateAccountService @Inject constructor(private val activateAccountClient: ActivateAccountClient) {
    suspend fun activateAccount(token: String): ActivateAccountResponse? {
        return withContext(Dispatchers.IO) {
            val request = ActivateAccountRequest(token)
            val response = activateAccountClient.activateAccount(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}