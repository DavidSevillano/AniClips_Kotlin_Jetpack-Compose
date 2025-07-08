package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.RegisterRepositoryImp
import com.burixer85.aniclips.data.service.RegisterService
import com.burixer85.aniclips.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Provides
    fun provideAuthRepository(registerService: RegisterService): RegisterRepository {
        return RegisterRepositoryImp(registerService)
    }
}