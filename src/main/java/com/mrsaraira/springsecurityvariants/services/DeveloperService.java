package com.mrsaraira.springsecurityvariants.services;

import com.mrsaraira.springsecurityvariants.model.Developer;

import java.util.List;

public interface DeveloperService {

    List<Developer> getAllDevelopers();

    Developer getDeveloper(Long id);

}
