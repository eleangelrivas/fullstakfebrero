package com.elengel.api.fullstack.controller;

import com.elengel.api.fullstack.config.rabbit.VentaProducer;
import com.elengel.api.fullstack.dto.VentaDTO;
import com.elengel.api.fullstack.persistence.entity.DetalleVenta;
import com.elengel.api.fullstack.persistence.entity.Venta;
import com.elengel.api.fullstack.service.DetalleVentaService;
import com.elengel.api.fullstack.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private VentaProducer ventaProducer;

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<Page<VentaDTO>> findAll(Pageable pageable) {
        Page<VentaDTO> ventas = ventaService.findAll(pageable);
        return ResponseEntity.ok(ventas);
    }

    /*@GetMapping
    public ResponseEntity<Page<Venta>> findAll(Pageable pageable) {
        Page<Venta> ventas = ventaService.findAll(pageable);
        return ResponseEntity.ok(ventas);
    }*/

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping("/{id}")
    public ResponseEntity<Venta> findOneById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.findOneById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleVenta>> findDetallesByVentaId(@PathVariable Long id) {
        List<DetalleVenta> detalles = detalleVentaService.findByVentaId(id);
        return ResponseEntity.ok(detalles);
    }

    /*para rabbitmq usaremos el controller ventas*/
    @PreAuthorize("hasAuthority('CREATE_ONE_PRODUCT')")
    @PostMapping
    public ResponseEntity<String> createVenta(@RequestBody VentaDTO ventaDTO) {
        // Aquí podrías validar o transformar el DTO si es necesario
        // Enviar la venta a la cola
        ventaProducer.sendVentaToQueue(ventaDTO);

        // Devolver respuesta
        return ResponseEntity.ok("Venta registrada en la cola para su procesamiento.");
    }
}