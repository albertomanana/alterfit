package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.Pago;
import com.atlasgym.os.model.enums.StatusPago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuarioId);
    List<Pago> findByEstado(StatusPago estado);
}
