package com.burixer85.aniclips.data.repository

import com.burixer85.aniclips.data.response.profile.GetMyProfileResponse
import com.burixer85.aniclips.data.response.profile.toDomain
import com.burixer85.aniclips.data.service.profile.ProfileService
import com.burixer85.aniclips.domain.model.main.profile.Profile
import com.burixer85.aniclips.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(val profileService: ProfileService) :
    ProfileRepository {
    override suspend fun getMyProfile(token: String): Profile? {
        val getMyProfileResponse: GetMyProfileResponse? = profileService.getMyProfile(token)

        return getMyProfileResponse?.toDomain()
    }
}