package com.burixer85.aniclips.domain.usecase.main.home

import com.burixer85.aniclips.domain.model.main.home.GetAllClips
import com.burixer85.aniclips.domain.model.main.home.OperationResultHome
import com.burixer85.aniclips.domain.repository.ClipRepository
import javax.inject.Inject

class GetAllClipsUseCase @Inject constructor(val clipRepository: ClipRepository) {
    suspend operator fun invoke(
        page: Int,
        size: Int,
        token: String
    ): OperationResultHome<GetAllClips> {

        val clip = clipRepository.getAllClips(page, size, token)

        return if (clip != null) OperationResultHome.Success(clip) else OperationResultHome.NetworkError
    }
}