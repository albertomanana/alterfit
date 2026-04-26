package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.MembresiaUsuario;
import com.atlasgym.os.model.enums.StatusMembresia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembresiaUsuarioRepository extends JpaRepository<MembresiaUsuario, Long> {
    List<MembresiaUsuario> findByUsuarioId(Long usuarioId);
    List<MembresiaUsuario> findByEstado(StatusMembresia estado);
}
