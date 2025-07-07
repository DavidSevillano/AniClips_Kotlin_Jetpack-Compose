package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.LoginRepositoryImp
import com.burixer85.aniclips.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImp
    ): LoginRepository
}