package com.mrsaraira.springsecurityvariants.security;

import lombok.Value;

@Value
public class AuthenticationResponse {

    private String email;
    private String token;

}
