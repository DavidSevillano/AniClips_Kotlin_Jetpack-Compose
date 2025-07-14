package com.burixer85.aniclips.domain.model.main.home

data class Clip(
    val id: Int,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val date: String,
    val views: Int,
    val duration: Int,
    val user: UserClip,
    val likes: Int,
    val comments: Int,
    val averageRating: Double,
    val ratingsCount: Int,
    val liked: Boolean,
    val rated: Boolean,
    val following: Boolean,
    val commented: Boolean
)