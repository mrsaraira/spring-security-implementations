package com.mrsaraira.springsecurityvariants.controllers;

import com.mrsaraira.springsecurityvariants.model.Developer;
import com.mrsaraira.springsecurityvariants.services.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/developers")
public class DevelopersController {

    private final DeveloperService developerService;


    @GetMapping
    public ResponseEntity<List<Developer>> getDevelopers() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloper(id));
    }

}
