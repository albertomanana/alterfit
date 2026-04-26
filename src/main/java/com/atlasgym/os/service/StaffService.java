package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.StaffPerfil;
import com.atlasgym.os.repository.StaffPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffPerfilRepository staffRepository;

    public List<StaffPerfil> findAll() { return staffRepository.findAll(); }
    public Optional<StaffPerfil> findById(Long id) { return staffRepository.findById(id); }
    public StaffPerfil save(StaffPerfil staff) { return staffRepository.save(staff); }
}
