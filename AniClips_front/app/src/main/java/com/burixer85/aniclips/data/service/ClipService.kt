package com.burixer85.aniclips.data.service

import android.util.Log
import com.burixer85.aniclips.data.network.ClipClient
import com.burixer85.aniclips.data.response.GetClipsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClipService @Inject constructor(private val clipClient: ClipClient) {

    suspend fun getAllClips(page: Int, size: Int, token: String): GetClipsResponse? {
        return withContext(Dispatchers.IO) {
            val response = clipClient.getAllClips(page, size, token)
            if (response.isSuccessful) {
                Log.i("ClipService", "Respuesta: ${response.body()}")
                response.body()
            } else {
                Log.i("ClipService", "Error: ${response.errorBody()?.string()}")
                null
            }
        }
    }
}