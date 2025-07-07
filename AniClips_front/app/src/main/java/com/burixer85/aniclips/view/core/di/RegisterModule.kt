package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.RegisterRepositoryImp
import com.burixer85.aniclips.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RegisterModule {
    @Binds
    @Singleton
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImp
    ): RegisterRepository
}