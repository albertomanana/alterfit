package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Gasto;
import com.atlasgym.os.repository.GastoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GastoService {
    private final GastoRepository gastoRepository;

    public List<Gasto> findAll() { return gastoRepository.findAll(); }
    public Gasto save(Gasto gasto) { return gastoRepository.save(gasto); }
}
