package com.perpetualtutorial.tutorialshare.template;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@RestController
//@RequestMapping("/api")
public class Controller<S extends EntityServices<E, R>,
                        E extends DataModelTemplate,
                        R extends RepositoryTemplate<E>,
                        A extends RepresentationModelAssembler<E, EntityModel<E>>> {
    private final A assembler;
    private final S service;

    public Controller(S service, A assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    public S getService() {
        return service;
    }
    public A getAssembler() {
        return assembler;
    }

    //=======================CRUD=====================
    //--------------GET--------------
    //---------Root

    @GetMapping("/all")
    public CollectionModel<EntityModel<E>> all() {
        List<EntityModel<E>> modelList = service.findAll().stream().map(entity -> assembler.toModel(entity)).collect(Collectors.toList());

        return CollectionModel.of(modelList, linkTo(methodOn(Controller.class).all()).withSelfRel());
    }
    //---------Single
    @GetMapping("/{id}")
    public EntityModel<E> one(@PathVariable Long id) {
        return assembler.toModel(service.findById(id));
    }

    //--------------POST-------------
    @PostMapping("")
    public ResponseEntity<EntityModel<E>> newEntity(@RequestBody E newEntity) {
        EntityModel<E> entityModel = assembler.toModel(service.save(newEntity));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())   // Plug in Resr Link
                .body(entityModel);                                                     // HTTP 201: created status msg
    }

    //--------------PUT---------------
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<E>> replaceEntity(@RequestBody E newEntity, @PathVariable Long id) {
        EntityModel<E> entityModel = assembler.toModel(service.update(newEntity, id));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //--------------DELETE---------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();  // Return HTTP 204: no content response
    }

}
