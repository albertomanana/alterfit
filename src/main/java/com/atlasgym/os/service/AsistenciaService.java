package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Asistencia;
import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.repository.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;

    public List<Asistencia> findAll() { return asistenciaRepository.findAll(); }

    public Asistencia registrarCheckIn(Usuario usuario) {
        Asistencia asistencia = Asistencia.builder()
                .usuario(usuario)
                .fechaHora(LocalDateTime.now())
                .tipo("LIBRE")
                .build();
        return asistenciaRepository.save(asistencia);
    }
}
