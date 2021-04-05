package com.mrsaraira.springsecurityvariants.rest;

import com.mrsaraira.springsecurityvariants.model.Developer;
import com.mrsaraira.springsecurityvariants.services.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DevelopersController {

    private final DeveloperService developerService;


    @PreAuthorize("hasAuthority('Read')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Developer>> getDevelopers() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }


    @PreAuthorize("hasAuthority('Read')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloper(id));
    }


    @PreAuthorize("hasAuthority('Write')")
    @PostMapping
    public ResponseEntity<Developer> create(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.createDeveloper(developer));
    }


    @PreAuthorize("hasAuthority('Write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        developerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
