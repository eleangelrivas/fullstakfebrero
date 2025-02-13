package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.persistence.entity.security.Role;
import com.elengel.api.fullstack.persistence.repository.security.RoleRepository;
import com.elengel.api.fullstack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Value("${security-default-role}")
    private String default_role;
    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(default_role);
    }
}
