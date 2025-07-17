package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.response.home.GetAllClipsResponse
import com.burixer85.aniclips.data.response.home.toDomain
import com.burixer85.aniclips.data.service.home.ClipService
import com.burixer85.aniclips.domain.model.main.home.GetAllClips
import com.burixer85.aniclips.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(val clipService: ClipService) : HomeRepository {


    override suspend fun getAllClips(page: Int, size: Int, token: String): GetAllClips? {

        val getAllClipsResponse: GetAllClipsResponse? =
            clipService.getAllClips(page, size, token)

        return getAllClipsResponse?.toDomain()

    }
}