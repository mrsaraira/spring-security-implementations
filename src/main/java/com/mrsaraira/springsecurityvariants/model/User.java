package com.mrsaraira.springsecurityvariants.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User implements Persistable<String> {

    @Id
    private String id;
    private String email;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;


    @Override
    public boolean isNew() {
        return isBlank(id);
    }

}
