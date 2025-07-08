package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.LoginRepositoryImp
import com.burixer85.aniclips.data.service.LoginService
import com.burixer85.aniclips.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    fun bindLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImp(loginService)
    }
}