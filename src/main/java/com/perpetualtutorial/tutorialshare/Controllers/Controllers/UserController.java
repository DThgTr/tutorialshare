package com.perpetualtutorial.tutorialshare.Controllers.Controllers;

import com.perpetualtutorial.tutorialshare.Controllers.Controller;
import com.perpetualtutorial.tutorialshare.Controllers.ModelAssembler;
import com.perpetualtutorial.tutorialshare.Models.User.User;
import com.perpetualtutorial.tutorialshare.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController extends Controller<User, UserRepository> {
    public UserController(UserRepository repository, ModelAssembler<User> assembler) {
        super(repository, assembler, "users");
    }
}
