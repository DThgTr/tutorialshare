package com.perpetualtutorial.tutorialshare.Controllers.EntityControllers;

import com.perpetualtutorial.tutorialshare.Controllers.Controller;
import com.perpetualtutorial.tutorialshare.Models.Tutorial.Tutorial;
import com.perpetualtutorial.tutorialshare.Repositories.TutorialRepository;
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
@RequestMapping("api/tutorials")
public class TutorialController extends Controller<Tutorial, TutorialRepository, TutorialModelAssembler> {
    public TutorialController(TutorialRepository repository, TutorialModelAssembler assembler) {
        super(repository, assembler);
    }
    @Override
    @GetMapping("/all")
    public CollectionModel<EntityModel<Tutorial>> all() {
        List<EntityModel<Tutorial>> modelList = super.getRepository().findAll().stream().map(entity -> super.getAssembler().toModel(entity)).collect(Collectors.toList());
        return CollectionModel.of(modelList, linkTo(methodOn(TutorialController.class).all()).withSelfRel());
    }
}

