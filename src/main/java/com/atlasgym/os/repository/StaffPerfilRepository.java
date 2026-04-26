package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.StaffPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StaffPerfilRepository extends JpaRepository<StaffPerfil, Long> {
    Optional<StaffPerfil> findByUsuarioId(Long usuarioId);
}
