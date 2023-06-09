package com.perpetualtutorial.tutorialshare.feature.User;

import com.perpetualtutorial.tutorialshare.template.EntityServices;
import org.springframework.stereotype.Service;

@Service
public class UserService extends EntityServices<User, UserRepository> {
    public UserService(User user, UserRepository repository) {
        super(user, repository);
    }
}
