package com.example.AniClips.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

import com.example.AniClips.dto.user.EditSeguidoDto;
import com.example.AniClips.dto.user.signupLogin.CreateUserRequest;
import com.example.AniClips.error.ActivationExpiredException;
import com.example.AniClips.model.Perfil;
import com.example.AniClips.model.Usuario;
import com.example.AniClips.model.UserRole;
import com.example.AniClips.repo.UsuarioRepository;
import com.example.AniClips.security.security.jwt.refresh.RefreshTokenRepository;
import com.example.AniClips.security.util.MailtrapMailSender;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailtrapMailSender mailSender;
    private final RefreshTokenRepository refreshTokenRepository;
    //private final ResendMailSender mailSender;


    @Value("${activation.duration}")
    private int activationDuration;

    public Usuario createUser(CreateUserRequest createUserRequest) {
        Usuario user = Usuario.builder()
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email())
                .roles(Set.of(UserRole.USER))
                .activationToken(generateRandomActivationCode())
                .build();

        Perfil perfil = new Perfil();
        perfil.setUsuario(user);
        user.setPerfil(perfil);

        try {
            mailSender.sendMail(createUserRequest.email(), "Activación de cuenta", user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }


        return usuarioRepository.save(user);
    }

    public String generateRandomActivationCode() {
        int code = (int) (Math.random() * 900000) + 100000; // código de 6 dígitos entre 100000 y 999999
        return String.valueOf(code);
    }

    public Usuario activateAccount(String token) {

        return usuarioRepository.findByActivationToken(token)
                .filter(user -> ChronoUnit.MINUTES.between(Instant.now(), user.getCreatedAt()) - activationDuration < 0)
                .map(user -> {
                    user.setEnabled(true);
                    user.setActivationToken(null);
                    return usuarioRepository.save(user);
                })
                .orElseThrow(() -> new ActivationExpiredException("El código de activación no existe o ha caducado"));
    }

    @Transactional
    public Usuario seguir(Usuario usuario, EditSeguidoDto editSeguidoDto) {

        Usuario seguidor = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningun usuario con id " + usuario.getId()));

        Usuario seguido = usuarioRepository.findById(editSeguidoDto.seguidoId())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningun usuario con id " + editSeguidoDto.seguidoId()));

        seguidor.addSeguido(seguido);

        usuarioRepository.save(seguidor);

        return seguido;
    }

    @Transactional
    public void dejarDeSeguir(Usuario usuario, UUID seguidoId) {

        Usuario seguidor = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningun usuario con id " + usuario.getId()));

        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningun usuario con id " + seguidoId));

        seguidor.removeSeguido(seguido);

    }

    @Transactional
    public void eliminarUsario(UUID usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningun usuario con id " + usuarioId));

        usuario.eliminarSeguidos();

        refreshTokenRepository.deleteByUser(usuario);
        
        usuarioRepository.deleteById(usuarioId);

    }
}