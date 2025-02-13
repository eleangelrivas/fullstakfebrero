package com.elengel.api.fullstack.persistence.repository;

import com.elengel.api.fullstack.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}