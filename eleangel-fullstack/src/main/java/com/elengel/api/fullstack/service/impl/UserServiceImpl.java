package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.dto.SaveUser;
import com.elengel.api.fullstack.exception.InvalidPasswordException;
import com.elengel.api.fullstack.exception.ObjectNotFoundException;
import com.elengel.api.fullstack.persistence.entity.security.Role;
import com.elengel.api.fullstack.persistence.entity.security.User;
import com.elengel.api.fullstack.persistence.repository.security.UserRepository;
import com.elengel.api.fullstack.service.RoleService;
import com.elengel.api.fullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User registrOneCustomer(SaveUser newUser) {
        validatePassword(newUser);

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());

        Role defaultRole = roleService.findDefaultRole().orElseThrow(()-> new ObjectNotFoundException("Role por defecto no pudo ser recuperado"));

        user.setRole(defaultRole);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
       return userRepository.findByUsername(username);

    }


    private void validatePassword(SaveUser newUser) {

        if(!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("El password no coincide");
        }

        if(!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Los password no coinciden");
        }


    }

}
