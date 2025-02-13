package com.elengel.api.fullstack.persistence.repository.security;

import com.elengel.api.fullstack.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
