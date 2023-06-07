package com.perpetualtutorial.tutorialshare.Controllers.EntityControllers;

import com.perpetualtutorial.tutorialshare.Controllers.Controller;

import com.perpetualtutorial.tutorialshare.Models.Tutorial.Tutorial;
import com.perpetualtutorial.tutorialshare.Models.User.User;
import com.perpetualtutorial.tutorialshare.Repositories.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/users")
public class UserController extends Controller<User, UserRepository, UserModelAssembler> {
    public UserController(UserRepository repository, UserModelAssembler assembler) {
        super(repository, assembler);
    }
    @Override
    @GetMapping("/all")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> modelList = super.getRepository().findAll().stream().map(entity -> super.getAssembler().toModel(entity)).collect(Collectors.toList());
        return CollectionModel.of(modelList, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }
}
