package com.perpetualtutorial.tutorialshare.Controllers.Controllers;

import com.perpetualtutorial.tutorialshare.Controllers.Controller;

import com.perpetualtutorial.tutorialshare.Models.User.User;
import com.perpetualtutorial.tutorialshare.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController extends Controller<User, UserRepository, UserModelAssembler> {
    public UserController(UserRepository repository, UserModelAssembler assembler) {
        super(repository, assembler);
    }
}
