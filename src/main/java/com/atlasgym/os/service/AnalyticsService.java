package com.atlasgym.os.service;

import com.atlasgym.os.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final UsuarioRepository usuarioRepository;
    private final MembresiaUsuarioRepository membresiaRepository;
    private final PagoRepository pagoRepository;
    private final GastoRepository gastoRepository;
    private final AsistenciaRepository asistenciaRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsuarios", usuarioRepository.count());
        stats.put("membresiasActivas", membresiaRepository.findAll().size()); // Simplified
        
        BigDecimal ingresos = pagoRepository.findAll().stream()
                .filter(p -> "PAGADO".equals(p.getEstado().name()))
                .map(p -> p.getMonto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal egresos = gastoRepository.findAll().stream()
                .map(g -> g.getMonto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        stats.put("ingresosTotales", ingresos);
        stats.put("egresosTotales", egresos);
        stats.put("balance", ingresos.subtract(egresos));
        stats.put("asistenciasHoy", asistenciaRepository.findAll().size()); // Simplified
        
        return stats;
    }
}
