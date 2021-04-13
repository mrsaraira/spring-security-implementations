package com.mrsaraira.springsecurityvariants.security;

import lombok.Value;

@Value
public class AuthenticationRequestDTO {

    private String email;
    private String password;

}
