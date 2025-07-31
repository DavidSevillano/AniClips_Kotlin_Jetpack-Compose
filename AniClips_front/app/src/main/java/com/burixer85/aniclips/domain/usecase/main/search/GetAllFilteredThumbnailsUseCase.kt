package com.burixer85.aniclips.domain.usecase.main.search

import com.burixer85.aniclips.domain.model.main.search.GetAllFilteredThumbnails
import com.burixer85.aniclips.domain.model.main.search.OperationResultSearch
import com.burixer85.aniclips.domain.repository.SearchRepository
import javax.inject.Inject

class GetAllFilteredThumbnailsUseCase @Inject constructor(val searchRepository: SearchRepository) {
    suspend operator fun invoke(
        search: String,
        page: Int,
        size: Int
    ): OperationResultSearch<GetAllFilteredThumbnails> {

        val thumbnail = searchRepository.getAllFilteredThumbnails(search, page, size)

        return if (thumbnail != null) OperationResultSearch.Success(thumbnail) else OperationResultSearch.NetworkError
    }
}