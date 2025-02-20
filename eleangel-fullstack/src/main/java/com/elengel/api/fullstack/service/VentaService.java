package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.dto.VentaDTO;
import com.elengel.api.fullstack.persistence.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VentaService {
    //Page<Venta> findAll(Pageable pageable);
    Page<VentaDTO> findAll(Pageable pageable);
    Optional<Venta> findOneById(Long id);

    Venta guardarVenta(VentaDTO ventaDTO);

}