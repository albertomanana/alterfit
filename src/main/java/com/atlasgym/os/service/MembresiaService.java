package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.MembresiaUsuario;
import com.atlasgym.os.model.entity.Pago;
import com.atlasgym.os.model.entity.Plan;
import com.atlasgym.os.model.enums.StatusMembresia;
import com.atlasgym.os.model.enums.StatusPago;
import com.atlasgym.os.repository.MembresiaUsuarioRepository;
import com.atlasgym.os.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembresiaService {
    private final MembresiaUsuarioRepository membresiaRepository;
    private final PagoRepository pagoRepository;

    public List<MembresiaUsuario> findAll() { return membresiaRepository.findAll(); }

    @Transactional
    public MembresiaUsuario contratarPlan(MembresiaUsuario membresia) {
        Plan plan = membresia.getPlan();
        membresia.setFechaInicio(LocalDate.now());
        membresia.setFechaFin(LocalDate.now().plusMonths(plan.getDuracionMeses()));
        membresia.setEstado(StatusMembresia.ACTIVA);
        
        MembresiaUsuario saved = membresiaRepository.save(membresia);
        
        // Generar primer pago
        Pago pago = Pago.builder()
                .usuario(saved.getUsuario())
                .membresia(saved)
                .monto(plan.getPrecio())
                .fechaVencimiento(LocalDateTime.now().plusDays(3))
                .estado(StatusPago.PENDIENTE)
                .referencia("MEM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .build();
        
        pagoRepository.save(pago);
        
        return saved;
    }
}
