package com.mrsaraira.springsecurityvariants.classes;

import com.mrsaraira.springsecurityvariants.model.Role;
import com.mrsaraira.springsecurityvariants.model.Status;
import com.mrsaraira.springsecurityvariants.model.User;
import com.mrsaraira.springsecurityvariants.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class DbInitializeRunner {

    private final UserRepository userRepository;


    @EventListener(ContextRefreshedEvent.class)
    public void initDB() {
        log.info("Initializing DB...");
        log.info("Clearing All Users...");
        userRepository.deleteAll();

        userRepository.saveAll(Arrays.asList(
                new User(null,
                        "admin@email.com",
                        "$2y$15$2l0bP31XKcAHyldsvdSuOOzwkxMEGIMS5Is1RTcBpM1omZaTiFd02",
                        "Admin",
                        "Adminovich",
                        Role.ADMIN,
                        Status.ACTIVE),
                new User(null,
                        "user@email.com",
                        "$2y$15$TnXeMTZriKrd9EPt24mefuj7j.Wz5yVHfs/PZu2D.WzIXPZ6aRP2K",
                        "User",
                        "Userovich",
                        Role.USER,
                        Status.BANNED))
        );
        log.info("Created default users: admin@email.com - admin, (BANNED) user@email.com - user");
    }

}
