package com.nisumexercise.userapirestful.controller;

import com.nisumexercise.userapirestful.dto.AuthRequest;
import com.nisumexercise.userapirestful.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @ApiOperation("Genera token de acceso")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
            );

        }catch (Exception e){
            throw new Exception("invalid user");

        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

}
