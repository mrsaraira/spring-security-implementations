package com.mrsaraira.springsecurityvariants.services;

import com.mrsaraira.springsecurityvariants.model.User;
import com.mrsaraira.springsecurityvariants.repositories.UserRepository;
import com.mrsaraira.springsecurityvariants.security.AuthenticationResponse;
import com.mrsaraira.springsecurityvariants.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthenticationResponse authenticate(String email, String password) {
        Assert.hasText(email, "Email cannot be blank");
        Assert.hasText(email, "Password cannot be blank");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User authUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        String token = jwtTokenProvider.createToken(authUser.getEmail(), authUser.getRole().name());// TODO: check for null

        return new AuthenticationResponse(email, token);
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(request, "HTTP request cannot be null");
        Assert.notNull(response, "HTTP response cannot be null");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

}
