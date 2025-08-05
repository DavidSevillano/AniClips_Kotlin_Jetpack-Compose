package com.burixer85.aniclips.data.di

import com.burixer85.aniclips.data.repository.ProfileRepositoryImp
import com.burixer85.aniclips.data.service.profile.ProfileService
import com.burixer85.aniclips.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    fun bindProfileRepository(profileService: ProfileService): ProfileRepository {
        return ProfileRepositoryImp(profileService)
    }
}