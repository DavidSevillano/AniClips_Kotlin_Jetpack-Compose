package com.burixer85.aniclips.data.di

import com.burixer85.aniclips.data.repository.SearchRepositoryImp
import com.burixer85.aniclips.data.service.search.FilteredThumbnailService
import com.burixer85.aniclips.data.service.search.ThumbnailService
import com.burixer85.aniclips.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    @Provides
    fun bindSearchRepository(
        thumbnailService: ThumbnailService,
        filteredThumbnailService: FilteredThumbnailService
    ): SearchRepository {
        return SearchRepositoryImp(thumbnailService, filteredThumbnailService)
    }
}