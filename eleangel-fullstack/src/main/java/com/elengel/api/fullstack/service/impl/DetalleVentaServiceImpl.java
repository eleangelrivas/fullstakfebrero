package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.persistence.entity.DetalleVenta;
import com.elengel.api.fullstack.persistence.repository.DetalleVentaRepository;
import com.elengel.api.fullstack.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> findByVentaId(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }
}