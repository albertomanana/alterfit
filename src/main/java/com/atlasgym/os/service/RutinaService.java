package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Rutina;
import com.atlasgym.os.repository.RutinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RutinaService {
    private final RutinaRepository rutinaRepository;

    public List<Rutina> findAll() { return rutinaRepository.findAll(); }
    public List<Rutina> findByUsuario(Long usuarioId) { return rutinaRepository.findByUsuarioId(usuarioId); }
    
    public Rutina save(Rutina rutina) {
        if (rutina.getFechaCreacion() == null) {
            rutina.setFechaCreacion(LocalDateTime.now());
        }
        return rutinaRepository.save(rutina);
    }
}
