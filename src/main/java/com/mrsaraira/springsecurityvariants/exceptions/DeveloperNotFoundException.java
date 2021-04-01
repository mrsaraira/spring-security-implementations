package com.mrsaraira.springsecurityvariants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND,
        reason = "Developer not found with the given identifier")
public class DeveloperNotFoundException extends RuntimeException {

}
