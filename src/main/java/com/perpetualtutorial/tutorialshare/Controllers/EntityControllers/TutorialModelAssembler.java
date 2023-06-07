package com.perpetualtutorial.tutorialshare.Controllers.EntityControllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perpetualtutorial.tutorialshare.Models.Tutorial.Tutorial;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public
class TutorialModelAssembler implements RepresentationModelAssembler<Tutorial, EntityModel<Tutorial>> {
    @Override
    public EntityModel<Tutorial> toModel(Tutorial tutorial){
        //Return an EntityModel containing a self-link of recipe obj arg & root link
        return EntityModel.of(tutorial,
                linkTo(methodOn(TutorialController.class).one(tutorial.getId())).withSelfRel(), //Self link
                linkTo(methodOn(TutorialController.class).all()).withRel("tutorials"));                //Root link
    }
}
