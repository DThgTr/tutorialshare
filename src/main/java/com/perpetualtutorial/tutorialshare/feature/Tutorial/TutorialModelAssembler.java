package com.perpetualtutorial.tutorialshare.feature.Tutorial;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class TutorialModelAssembler implements RepresentationModelAssembler<Tutorial, EntityModel<Tutorial>> {
    @Override
    public EntityModel<Tutorial> toModel(Tutorial tutorial){
        //Return an EntityModel containing a self-link of recipe obj arg & root link
        return EntityModel.of(tutorial,
                WebMvcLinkBuilder.linkTo(methodOn(TutorialController.class).one(tutorial.getId())).withSelfRel(), //Self link
                WebMvcLinkBuilder.linkTo(methodOn(TutorialController.class).all()).withRel("tutorials"));                //Root link
    }
}
