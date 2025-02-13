package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.persistence.entity.Cliente;
import com.elengel.api.fullstack.persistence.repository.ClienteRepository;
import com.elengel.api.fullstack.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public Optional<Cliente> findOneById(Long id) {
        return clienteRepository.findById(id);
    }
}