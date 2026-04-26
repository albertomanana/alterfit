package com.atlasgym.os.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones_clases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SesionClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffPerfil instructor;

    @Column(nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(nullable = false)
    private LocalDateTime fechaHoraFin;

    private Integer cupoActual = 0;

    private String estado; // "PROGRAMADA", "COMPLETADA", "CANCELADA"
}
