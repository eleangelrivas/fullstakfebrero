package com.elengel.api.fullstack.controller;

import com.elengel.api.fullstack.dto.AuthenticationRequest;
import com.elengel.api.fullstack.dto.AuthenticationResponse;
import com.elengel.api.fullstack.persistence.entity.security.User;
import com.elengel.api.fullstack.service.auth.AuthenticateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticateService authenticationService;


    @PreAuthorize("permitAll")
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenvalid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenvalid);

    }


    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse resp = authenticationService.login(authenticationRequest);

        return ResponseEntity.ok(resp);

    }

    @PreAuthorize("hasAuthority('READ_MY_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }

}
