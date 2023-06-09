package com.perpetualtutorial.tutorialshare.feature.User;

import com.perpetualtutorial.tutorialshare.template.Controller;
import com.perpetualtutorial.tutorialshare.template.DataModelTemplate;
import com.perpetualtutorial.tutorialshare.template.EntityServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/users")
public class UserController extends Controller<UserService,
                                                User,
                                                UserRepository,
                                                UserModelAssembler> {
    public UserController(UserService service, UserModelAssembler assembler) {
        super(service, assembler);
    }
    @Override
    @GetMapping("/all")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> modelList = super.getService().findAll().stream().map(entity -> super.getAssembler().toModel(entity)).collect(Collectors.toList());

        return CollectionModel.of(modelList, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

}
