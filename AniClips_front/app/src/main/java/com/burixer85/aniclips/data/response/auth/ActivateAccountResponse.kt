package com.burixer85.aniclips.data.response.auth

import com.burixer85.aniclips.domain.model.auth.UserActivateAccount
import java.util.UUID

data class ActivateAccountResponse(
    val id: UUID,
    val username: String,
    val roles: List<String>
)

fun ActivateAccountResponse.toDomain(): UserActivateAccount {
    return UserActivateAccount(
        id = id,
        username = username,
        role = roles,
    )
}
