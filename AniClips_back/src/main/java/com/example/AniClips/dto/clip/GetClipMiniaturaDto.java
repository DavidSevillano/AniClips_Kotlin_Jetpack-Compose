package com.example.AniClips.dto.clip;

import com.example.AniClips.model.Clip;

public record GetClipMiniaturaDto(
        Long id,
        String miniatura,
        String nombreAnime
) {
    public static GetClipMiniaturaDto of(Clip clip) {
        return new GetClipMiniaturaDto(
                clip.getId(),
                clip.getMiniatura(),
                clip.getNombreAnime()
        );
    }
}
