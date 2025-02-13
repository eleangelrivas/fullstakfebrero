package com.elengel.api.fullstack.controller;

import com.elengel.api.fullstack.persistence.entity.Cliente;
import com.elengel.api.fullstack.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("permitAll")
    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
        Page<Cliente> clientes = clienteService.findAll(pageable);
        return ResponseEntity.ok(clientes);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findOneById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findOneById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}