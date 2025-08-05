package com.burixer85.aniclips.data.service.profile

import com.burixer85.aniclips.data.network.profile.ProfileClient
import com.burixer85.aniclips.data.response.profile.GetMyProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileService @Inject constructor(val profileClient: ProfileClient) {
    suspend fun getMyProfile(token: String): GetMyProfileResponse? {
        return withContext(Dispatchers.IO) {
            val response = profileClient.getMyProfile(token)
            android.util.Log.d("ProfileDebug", "HTTP response: $response")
            if (response.isSuccessful) {
                android.util.Log.d("ProfileDebug", "Body: ${response.body()}")
                response.body()
            } else {
                null
            }
        }
    }
}