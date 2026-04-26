package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessSessionService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

    private static final String SESSION_USER_ID = "USER_ID";

    public boolean login(String identifier, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmailOrUsername(identifier, identifier);
        
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.isActivo() && passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute(SESSION_USER_ID, user.getId());
                user.setUltimoAcceso(LocalDateTime.now());
                usuarioRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public void logout() {
        session.invalidate();
    }

    public Optional<Usuario> getCurrentUser() {
        Long userId = (Long) session.getAttribute(SESSION_USER_ID);
        if (userId != null) {
            return usuarioRepository.findById(userId);
        }
        return Optional.empty();
    }

    public boolean isAuthenticated() {
        return session.getAttribute(SESSION_USER_ID) != null;
    }
}
