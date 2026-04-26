package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Gasto;
import com.atlasgym.os.model.entity.Nomina;
import com.atlasgym.os.repository.NominaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NominaService {
    private final NominaRepository nominaRepository;
    private final GastoService gastoService;

    public List<Nomina> findAll() { return nominaRepository.findAll(); }

    @Transactional
    public Nomina emitirNomina(Nomina nomina) {
        nomina.setEstado("EMITIDA");
        nomina.setReferencia("NOM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        Nomina saved = nominaRepository.save(nomina);
        
        // Crear gasto asociado
        Gasto gasto = Gasto.builder()
                .concepto("Nómina: " + saved.getStaff().getUsuario().getNombreCompleto())
                .monto(saved.getMontoNeto())
                .fecha(LocalDateTime.now())
                .categoria("NOMINA")
                .estado("PENDIENTE")
                .build();
        
        gastoService.save(gasto);
        
        return saved;
    }
}
