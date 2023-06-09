package com.perpetualtutorial.tutorialshare.feature.User;

import com.perpetualtutorial.tutorialshare.template.EntityServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserService")
public class UserService extends EntityServices<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
