package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByUsuarioId(Long usuarioId);
    List<Rutina> findByActivaTrue();
}
