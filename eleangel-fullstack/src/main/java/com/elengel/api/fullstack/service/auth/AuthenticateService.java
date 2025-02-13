package com.elengel.api.fullstack.service.auth;

import com.elengel.api.fullstack.dto.AuthenticationRequest;
import com.elengel.api.fullstack.dto.AuthenticationResponse;
import com.elengel.api.fullstack.dto.RegisteredUser;
import com.elengel.api.fullstack.dto.SaveUser;
import com.elengel.api.fullstack.exception.ObjectNotFoundException;
import com.elengel.api.fullstack.persistence.entity.security.User;
import com.elengel.api.fullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticateService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public RegisteredUser registerOneCustomer(SaveUser newUser) {


        User user = userService.registrOneCustomer(newUser);
        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName());
        String jwt = jwtService.generateToken(user,generateExtraClaim(user));

        userDto.setJwt(jwt);

        return userDto;

    }

    private Map<String, Object> generateExtraClaim2(User user) {
        Map<String, Object>  extraClaims= new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getRole().getName());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }

    //refactorice, porque habia un problema en la asignacion de extraclaims, exatamente en los permisos,
    private Map<String, Object> generateExtraClaim(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("username", user.getUsername());
        extraClaims.put("role", user.getRole().getName());

        // Convertir a un Set para evitar duplicados
        Set<String> roles = user.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet()); // aqui la magina, eliminamos los duplicados

        extraClaims.put("authorities", roles);// y firmamos bien
        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest autRequest) {
       Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.getUsername(),autRequest.getPassword()
        );
        authenticationManager.authenticate(authentication);
        UserDetails user = userService.findOneByUsername(autRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user,generateExtraClaim((User) user));




        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);
        return authRsp;



    }

    public boolean validateToken(String jwt) {

        try {
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println("excepcion al validar tken: "+e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser(){
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String username = (String) auth.getPrincipal();
        return userService.findOneByUsername(username)
                .orElseThrow(()->new ObjectNotFoundException("El usuario no existe en la busqeuda del contexto logueado: "+username));

    }
}
