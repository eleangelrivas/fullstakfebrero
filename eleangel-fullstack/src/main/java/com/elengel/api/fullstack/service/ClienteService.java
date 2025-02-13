package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.persistence.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {
    Page<Cliente> findAll(Pageable pageable);
    Optional<Cliente> findOneById(Long id);
}