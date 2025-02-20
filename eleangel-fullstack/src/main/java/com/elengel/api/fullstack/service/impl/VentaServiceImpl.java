package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.dto.VentaDTO;
import com.elengel.api.fullstack.persistence.entity.DetalleVenta;
import com.elengel.api.fullstack.persistence.entity.Venta;
import com.elengel.api.fullstack.persistence.repository.ClienteRepository;
import com.elengel.api.fullstack.persistence.repository.DetalleVentaRepository;
import com.elengel.api.fullstack.persistence.repository.VentaRepository;
import com.elengel.api.fullstack.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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

    @Transactional
    @Override
    public Venta guardarVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());

        // primero asegurare, debido alas relaciones, que el cliente que se envia existe
        clienteRepository.findById(ventaDTO.getCliente().getId())
                .ifPresent(venta::setCliente);

        // usamos jpa para almacenar la venta
        venta = ventaRepository.save(venta);

        // y de detalles almacenamos el detalle de la venta
        List<DetalleVenta> detalles = ventaDTO.getDetalles();
        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(venta); // creamos la relacion
            detalleVentaRepository.save(detalle);
        }

        return venta;
    }

}