package com.burixer85.aniclips.domain.repository

import com.burixer85.aniclips.domain.model.main.profile.Profile

interface ProfileRepository {
    suspend fun getMyProfile(token: String): Profile?
}