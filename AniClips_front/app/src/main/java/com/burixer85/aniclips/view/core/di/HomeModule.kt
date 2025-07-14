package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.ClipRepositoryImp
import com.burixer85.aniclips.data.service.ClipService
import com.burixer85.aniclips.domain.repository.ClipRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    fun bindClipRepository(clipService: ClipService): ClipRepository {
        return ClipRepositoryImp(clipService)
    }
}