package com.example.AniClips.controllers;

import com.example.AniClips.dto.comentario.EditComentarioDto;
import com.example.AniClips.dto.comentario.GetComentarioDto;
import com.example.AniClips.model.Comentario;
import com.example.AniClips.model.Usuario;
import com.example.AniClips.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comentario/")
@Tag(name = "comentario", description = "Controlador comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Operation(summary = "Obtiene todos los comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado comentarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetComentarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "texto": "¡Esta batalla fue épica!",
                                                      "fecha": "2023-01-16",
                                                      "getUsuarioClipDto": {
                                                          "Username": "goku",
                                                          "getPerfilAvatarDto": {
                                                              "avatar": "https://example.com/goku"
                                                          }
                                                      }
                                                  },
                                                  {
                                                      "texto": "¡Siempre me emociona esta escena!",
                                                      "fecha": "2023-02-21",
                                                      "getUsuarioClipDto": {
                                                          "Username": "eren",
                                                          "getPerfilAvatarDto": {
                                                              "avatar": "https://example.com/eren"
                                                          }
                                                      }
                                                  },
                                                  {
                                                      "texto": "No hay mejor pelea en AOT.",
                                                      "fecha": "2023-03-11",
                                                      "getUsuarioClipDto": {
                                                          "Username": "naruto",
                                                          "getPerfilAvatarDto": {
                                                              "avatar": "https://example.com/naruto"
                                                          }
                                                      }
                                                  }
                                              ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado comentarios",
                    content = @Content),
    })
    @GetMapping
    public Page<GetComentarioDto> getAll(@RequestParam Long clipId, @RequestParam(defaultValue = "0") int page) {
        Pageable pageRequest = PageRequest.of(page, 8, Sort.by(Sort.Direction.DESC, "fecha"));
        return comentarioService.findAll(clipId, pageRequest)
                .map(GetComentarioDto::of);
    }

    @Operation(summary = "Añade un comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Comentario añadido",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EditComentarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 4,
                                                "fecha": "2025-02-22",
                                                "texto": "De locos"
                                            }
                                            """
                            )}
                    )}),
    })
    @PostMapping
    public ResponseEntity<Comentario> create(@AuthenticationPrincipal Usuario usuario, @RequestBody EditComentarioDto editComentarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        comentarioService.save(usuario, editComentarioDto));
    }

    @Operation(summary = "Eliminar un clip del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el clip",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permiso para eliminar este clip",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarMiComentario(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id) {

        comentarioService.eliminarMiComentario(usuario, id);

        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Eliminar un comentario de cualquier usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el comentario",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun comentario con id 1",
                    content = @Content)
    })
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id) {

        comentarioService.eliminarComentario(id);

        return ResponseEntity.noContent().build();

    }
}
