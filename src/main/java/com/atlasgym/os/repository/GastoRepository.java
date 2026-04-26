package com.atlasgym.os.repository;

import com.atlasgym.os.model.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
}
