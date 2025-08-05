package com.burixer85.aniclips.domain.model.main.profile

import com.burixer85.aniclips.domain.model.main.search.Thumbnail

data class Profile(
    val username: String,
    val foto: String,
    val numeroClips: Int,
    val numeroSeguidores: Int,
    val numeroSeguidos: Int,
    val descripcion: String,
    val clips: List<Thumbnail>
)
