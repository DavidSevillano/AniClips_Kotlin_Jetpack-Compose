package com.burixer85.aniclips.view.main.model

data class ProfileUi(
    val username: String = "",
    val avatar: String = "",
    val clipsCount: Int = 0,
    val followers: Int = 0,
    val followed: Int = 0,
    val description: String = "",
    val clips: List<ThumbnailUi> = emptyList()
)