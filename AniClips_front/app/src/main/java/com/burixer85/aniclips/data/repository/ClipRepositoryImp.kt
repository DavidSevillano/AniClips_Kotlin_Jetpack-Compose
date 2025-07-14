package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.response.GetClipsResponse
import com.burixer85.aniclips.data.response.toDomain
import com.burixer85.aniclips.data.service.ClipService
import com.burixer85.aniclips.domain.model.main.home.GetAllClips
import com.burixer85.aniclips.domain.repository.ClipRepository
import javax.inject.Inject

class ClipRepositoryImp @Inject constructor(val clipService: ClipService) : ClipRepository {


    override suspend fun getAllClips(page: Int, size: Int, token: String): GetAllClips? {

        val getClipsResponse: GetClipsResponse? =
            clipService.getAllClips(page, size, token)
        android.util.Log.d("ClipRepositoryImp", "Respuesta backend: $getClipsResponse")

        return getClipsResponse?.toDomain()

    }
}