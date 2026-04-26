package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Pago;
import com.atlasgym.os.model.enums.StatusPago;
import com.atlasgym.os.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;

    public List<Pago> findAll() { return pagoRepository.findAll(); }
    public Optional<Pago> findById(Long id) { return pagoRepository.findById(id); }

    @Transactional
    public void marcarComoPagado(Long id) {
        pagoRepository.findById(id).ifPresent(p -> {
            p.setEstado(StatusPago.PAGADO);
            p.setFechaPago(LocalDateTime.now());
            pagoRepository.save(p);
        });
    }
}
