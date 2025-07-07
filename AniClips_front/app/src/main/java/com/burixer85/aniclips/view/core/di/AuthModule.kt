package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.AuthRepositoryImp
import com.burixer85.aniclips.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImp
    ): AuthRepository
}