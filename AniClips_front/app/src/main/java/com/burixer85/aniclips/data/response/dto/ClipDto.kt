package com.burixer85.aniclips.data.response.dto

import com.burixer85.aniclips.domain.model.main.home.Clip

data class ClipDto(
    val id: Int,
    val descripcion: String,
    val urlVideo: String,
    val urlMiniatura: String,
    val fecha: String,
    val visitas: Int,
    val duracion: Int,
    val getUsuarioClipDto: UsuarioClipDto,
    val cantidadMeGusta: Int,
    val cantidadComentarios: Int,
    val mediaValoraciones: Double,
    val cantidadValoraciones: Int,
    val ledioLike: Boolean,
    val loRateo: Boolean,
    val loSigue: Boolean,
    val loComento: Boolean
)

fun ClipDto.toDomain(): Clip {
    return Clip(
        id = id,
        description = descripcion,
        videoUrl = urlVideo,
        thumbnailUrl = urlMiniatura,
        date = fecha,
        views = visitas,
        duration = duracion,
        user = getUsuarioClipDto.toDomain(),
        likes = cantidadMeGusta,
        comments = cantidadComentarios,
        averageRating = mediaValoraciones,
        ratingsCount = cantidadValoraciones,
        liked = ledioLike,
        rated = loRateo,
        following = loSigue,
        commented = loComento
    )
}