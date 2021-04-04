package com.mrsaraira.springsecurityvariants.services;

import com.mrsaraira.springsecurityvariants.exceptions.DeveloperNotFoundException;
import com.mrsaraira.springsecurityvariants.model.Developer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Mark", "Zuckerberg"),
            new Developer(2L, "Bill", "Gates"),
            new Developer(3L, "Pavel", "Durov")
    ).collect(Collectors.toList());


    @Override
    public List<Developer> getAllDevelopers() {
        return new ArrayList<>(DEVELOPERS);
    }


    @Override
    public Developer getDeveloper(Long id) {
        Assert.notNull(id, "Id cannot be null");
        return DEVELOPERS.stream().filter(developer -> id.equals(developer.getId())).findFirst().orElseThrow(DeveloperNotFoundException::new);
    }


    @Override
    public Developer createDeveloper(Developer developer) {
        log.info("Creating Developer {}", developer);
        Assert.notNull(developer, "Developer cannot be null");
        Assert.notNull(developer.getId(), "Id cannot be null");
        Assert.hasText(developer.getFirstName(), "First name cannot be blank");
        Assert.hasText(developer.getLastName(), "Last name cannot be blank");

        DEVELOPERS.add(developer);
        return developer;
    }


    @Override
    public void deleteById(Long id) {
        log.info("Deleting Developer with Id {}", id);
        DEVELOPERS.removeIf(developer -> id.equals(developer.getId()));
    }

}
