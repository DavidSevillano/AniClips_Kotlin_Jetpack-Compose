package com.example.AniClips.dto.perfil;

import com.example.AniClips.model.Perfil;

public record GetPerfilDto(
        String avatar,
        String descripcion
) {

    public static GetPerfilDto of (Perfil perfil){
        return new GetPerfilDto(
                perfil.getAvatar(),
                perfil.getDescripcion()
        );
    }


}
