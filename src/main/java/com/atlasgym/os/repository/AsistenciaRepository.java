package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByUsuarioId(Long usuarioId);
    List<Asistencia> findByFechaHoraBetween(LocalDateTime start, LocalDateTime end);
}
