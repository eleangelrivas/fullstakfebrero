package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.persistence.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> findByVentaId(Long ventaId);
}