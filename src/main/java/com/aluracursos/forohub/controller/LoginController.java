package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.user.AccountAuthenticationDto;
import com.aluracursos.forohub.infra.security.AccessTokenDto;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    @Operation(summary = "Authenticate user", description = "Authenticate user with username and password")
    public ResponseEntity authenticateUser(@RequestBody @Valid AccountAuthenticationDto userCredentialsDto) {
        Authentication authRequest = new UsernamePasswordAuthenticationToken(
                userCredentialsDto.username(), userCredentialsDto.password());
        var validatedUser = authManager.authenticate(authRequest);
        var accessToken = jwtService.createToken((User) validatedUser.getPrincipal());
        return ResponseEntity.ok(new AccessTokenDto(accessToken));
    }
}
