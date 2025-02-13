package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.dto.VentaDTO;
import com.elengel.api.fullstack.persistence.entity.Venta;
import com.elengel.api.fullstack.persistence.repository.VentaRepository;
import com.elengel.api.fullstack.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    /*@Override
    public Page<Venta> findAll(Pageable pageable) {
        return ventaRepository.findAll(pageable);
    }

    @Override
    public Page<VentaDTO> findAll(Pageable pageable) {
        // Obtener las ventas desde el repositorio
        Page<Venta> ventas = ventaRepository.findAll(pageable);

        // Mapear las ventas a VentaDTO
        return ventas.map(venta -> {
            VentaDTO dto = new VentaDTO();
            dto.setId(venta.getId());
            dto.setFecha(venta.getFecha());
            dto.setCliente(venta.getCliente());
            dto.setDetalles(venta.getDetalles());
            return dto;
        });
    }*/

    @Override
    public Page<VentaDTO> findAll(Pageable pageable) {
        System.out.println("Pageable - Page: " + pageable.getPageNumber() + ", Size: " + pageable.getPageSize());
        Page<Venta> ventas = ventaRepository.findAll(pageable);
        System.out.println("Total de elementos: " + ventas.getTotalElements());
        System.out.println("Total de páginas: " + ventas.getTotalPages());
        return ventas.map(venta -> {
            VentaDTO dto = new VentaDTO();
            dto.setId(venta.getId());
            dto.setFecha(venta.getFecha());
            dto.setCliente(venta.getCliente());
            dto.setDetalles(venta.getDetalles());
            return dto;
        });
    }

    @Override
    public Optional<Venta> findOneById(Long id) {
        return ventaRepository.findById(id);
    }


}