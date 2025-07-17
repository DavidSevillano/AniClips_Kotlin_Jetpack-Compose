package com.burixer85.aniclips.domain.usecase.main.search

import com.burixer85.aniclips.domain.model.main.search.GetAllThumbnails
import com.burixer85.aniclips.domain.model.main.search.OperationResultSearch
import com.burixer85.aniclips.domain.repository.SearchRepository
import javax.inject.Inject

class GetAllThumbnailsUseCase @Inject constructor(val searchRepository: SearchRepository) {
    suspend operator fun invoke(page: Int, size: Int): OperationResultSearch<GetAllThumbnails> {

        val thumbnail = searchRepository.getAllThumbnails(page, size)

        return if (thumbnail != null) OperationResultSearch.Success(thumbnail) else OperationResultSearch.NetworkError
    }
}