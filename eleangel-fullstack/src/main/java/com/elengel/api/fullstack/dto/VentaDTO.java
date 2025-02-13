package com.elengel.api.fullstack.dto;

import com.elengel.api.fullstack.persistence.entity.Cliente;
import com.elengel.api.fullstack.persistence.entity.DetalleVenta;
import java.time.LocalDateTime;
import java.util.List;

public class VentaDTO {
    private Long id;
    private LocalDateTime fecha;
    private Cliente cliente;
    private List<DetalleVenta> detalles;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}