package com.burixer85.aniclips.view.main.model

data class ClipUi(
    val id: String,
    val username: String,
    val avatar: String,
    val video: String,
    val thumbnail: String,
    val likes: Int,
    val comments: Int,
    val ratingsCount: Int,
    val description: String,
    val date: String
)