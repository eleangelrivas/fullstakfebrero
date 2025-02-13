package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findDefaultRole();
}
