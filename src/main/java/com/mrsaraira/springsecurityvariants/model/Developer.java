package com.mrsaraira.springsecurityvariants.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    private Long id;
    private String firstName;
    private String lastName;

}
