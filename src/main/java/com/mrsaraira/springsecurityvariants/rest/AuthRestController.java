package com.mrsaraira.springsecurityvariants.rest;

import com.mrsaraira.springsecurityvariants.security.AuthenticationRequestDTO;
import com.mrsaraira.springsecurityvariants.security.AuthenticationResponse;
import com.mrsaraira.springsecurityvariants.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            AuthenticationResponse authResponse = authenticationService.authenticate(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid email or password combination", HttpStatus.FORBIDDEN);
        }

    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok().build();
    }

}
