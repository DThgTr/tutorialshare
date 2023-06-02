package com.perpetualtutorial.tutorialshare.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perpetualtutorial.tutorialshare.Models.EntityServices;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public //Spring annotation: auto create assembler when app start
class ModelAssembler<E extends EntityServices<E>> implements RepresentationModelAssembler<E, EntityModel<E>> {
    private String rootLink;

    public EntityModel<E> toModel(E genericModel, String rootLink) {
        this.rootLink = rootLink;
        return toModel(genericModel);
    }

    @Override
    public EntityModel<E> toModel(E genericModel){
        //Return an EntityModel containing a self-link of recipe obj arg & root link
        return EntityModel.of(genericModel,
                linkTo(methodOn(Controller.class).one(genericModel.getId())).withSelfRel(), //Self link
                linkTo(methodOn(Controller.class).all()).withRel(rootLink));                //Root link
    }
}