package com.perpetualtutorial.tutorialshare;

import com.perpetualtutorial.tutorialshare.feature.Tutorial.Tutorial;
import com.perpetualtutorial.tutorialshare.feature.Tutorial.TutorialRepository;
import com.perpetualtutorial.tutorialshare.feature.User.User;
import com.perpetualtutorial.tutorialshare.feature.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(TutorialRepository tutRepository, UserRepository userRepository) {
        return args -> {
            User initUser = new User("username 1", "pwd 1", "mail 1");
            log.info("Preloading " + userRepository.save(initUser));
            Tutorial initTut = new Tutorial("tut title 1", "tut desc 1", "tut content 1", initUser.getId());
            log.info("Preloading " + tutRepository.save(initTut));
        };
    }

}
