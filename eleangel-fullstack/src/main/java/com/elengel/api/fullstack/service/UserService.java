package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.dto.SaveUser;
import com.elengel.api.fullstack.persistence.entity.security.User;

import java.util.Optional;


public interface UserService {


    User registrOneCustomer(SaveUser newUser);

    Optional <User> findOneByUsername(String username);


}
