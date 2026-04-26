package com.atlasgym.os.config;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.model.enums.AccessProfile;
import com.atlasgym.os.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthBootstrapRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.auth.bootstrap-password}")
    private String bootstrapPassword;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            log.info("Iniciando secuencia de bootstrap de usuarios...");
            
            Usuario admin = Usuario.builder()
                    .nombre("Administrador")
                    .apellidos("AtlasGym")
                    .username("admin")
                    .email("admin@atlasgym.com")
                    .password(passwordEncoder.encode(bootstrapPassword))
                    .perfil(AccessProfile.ADMIN)
                    .activo(true)
                    .mustChangePassword(false)
                    .build();
            
            usuarioRepository.save(admin);
            log.info("Usuario ADMIN creado con éxito. Username: admin, Password: {}", bootstrapPassword);
        }
    }
}
