package com.elengel.api.fullstack.persistence.repository.security;

import com.elengel.api.fullstack.persistence.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String defaultRole);
}
