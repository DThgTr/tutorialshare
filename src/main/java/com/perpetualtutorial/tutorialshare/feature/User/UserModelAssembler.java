package com.perpetualtutorial.tutorialshare.feature.User;

import com.perpetualtutorial.tutorialshare.feature.User.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user){
        //Return an EntityModel containing a self-link of recipe obj arg & root link
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(), //Self link
                linkTo(methodOn(UserController.class).all()).withRel("users"));         //Root link
    }
}
