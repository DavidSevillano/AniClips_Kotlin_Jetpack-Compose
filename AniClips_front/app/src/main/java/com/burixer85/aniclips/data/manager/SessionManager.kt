package com.burixer85.aniclips.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val ID_KEY = stringPreferencesKey("user_id")
        private val USERNAME_KEY = stringPreferencesKey("user_username")
        private val AVATAR_KEY = stringPreferencesKey("user_avatar")
        private val ROLE_KEY = stringPreferencesKey("user_role")
        private val TOKEN_KEY = stringPreferencesKey("user_token")
    }

    suspend fun saveUserLogin(
        id: UUID,
        username: String,
        avatar: String,
        role: List<String>,
        token: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[ID_KEY] = id.toString()
            prefs[USERNAME_KEY] = username
            prefs[AVATAR_KEY] = avatar
            prefs[ROLE_KEY] = role.joinToString(",")
            prefs[TOKEN_KEY] = token
        }
    }

    fun getUserLogin() = context.dataStore.data.map { prefs ->
        val id = prefs[ID_KEY]?.let { UUID.fromString(it) }
        val username = prefs[USERNAME_KEY] ?: ""
        val avatar = prefs[AVATAR_KEY] ?: ""
        val role = prefs[ROLE_KEY]?.split(",") ?: emptyList()
        val token = prefs[TOKEN_KEY] ?: ""
        if (id != null && username.isNotEmpty() && token.isNotEmpty()) {
            com.burixer85.aniclips.domain.model.UserLogin(id, username, avatar, role, token)
        } else {
            null
        }
    }

    suspend fun clearUserLogin() {
        context.dataStore.edit { prefs ->
            prefs.remove(ID_KEY)
            prefs.remove(USERNAME_KEY)
            prefs.remove(AVATAR_KEY)
            prefs.remove(ROLE_KEY)
            prefs.remove(TOKEN_KEY)
        }
    }
}