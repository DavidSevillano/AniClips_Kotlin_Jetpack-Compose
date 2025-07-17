package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.AuthRepositoryImp
import com.burixer85.aniclips.data.service.auth.ActivateAccountService
import com.burixer85.aniclips.data.service.auth.LoginService
import com.burixer85.aniclips.data.service.auth.RegisterService
import com.burixer85.aniclips.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideAuthRepository(
        loginService: LoginService,
        registerService: RegisterService,
        activateAccountService: ActivateAccountService
    ): AuthRepository {
        return AuthRepositoryImp(loginService, registerService, activateAccountService)
    }
}