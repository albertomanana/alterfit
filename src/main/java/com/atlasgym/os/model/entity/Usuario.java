package com.atlasgym.os.model.entity;

import com.atlasgym.os.model.enums.AccessProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessProfile perfil;

    private boolean activo = true;

    private boolean mustChangePassword = false;

    private LocalDateTime ultimoAcceso;

    private String fotoUrl;

    public String getNombreCompleto() {
        return (nombre != null ? nombre : "") + " " + (apellidos != null ? apellidos : "");
    }
}
