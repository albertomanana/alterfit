package com.atlasgym.os.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer duracionMinutos = 60;

    private Integer cupoMaximo = 20;

    private boolean activa = true;
}
