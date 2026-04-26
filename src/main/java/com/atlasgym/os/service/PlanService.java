package com.atlasgym.os.service;

import com.atlasgym.os.model.entity.Plan;
import com.atlasgym.os.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public List<Plan> findAll() { return planRepository.findAll(); }
    public List<Plan> findActivos() { return planRepository.findByActivoTrue(); }
    public Optional<Plan> findById(Long id) { return planRepository.findById(id); }
    public Plan save(Plan plan) { return planRepository.save(plan); }
}
