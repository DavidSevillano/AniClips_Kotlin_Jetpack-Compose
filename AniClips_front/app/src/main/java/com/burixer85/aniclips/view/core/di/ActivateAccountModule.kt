package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.repository.ActivateAccountRepositoryImp
import com.burixer85.aniclips.data.service.ActivateAccountService
import com.burixer85.aniclips.domain.repository.ActivateAccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ActivateAccountModule {
    @Provides
    fun bindActivateAccountRepository(loginService: ActivateAccountService): ActivateAccountRepository {
        return ActivateAccountRepositoryImp(loginService)
    }
}