package com.burixer85.aniclips.domain.usecase.main.profile

import com.burixer85.aniclips.domain.model.main.profile.OperationResultProfile
import com.burixer85.aniclips.domain.model.main.profile.Profile
import com.burixer85.aniclips.domain.repository.ProfileRepository
import javax.inject.Inject

class GetMyProfileUseCase @Inject constructor(val profileRepository: ProfileRepository) {
    suspend operator fun invoke(token: String): OperationResultProfile<Profile> {
        val myProfile = profileRepository.getMyProfile(token)

        return if (myProfile != null) OperationResultProfile.Success(myProfile) else OperationResultProfile.NetworkError

    }
}