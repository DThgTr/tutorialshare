package com.perpetualtutorial.tutorialshare.Controllers;

import com.perpetualtutorial.tutorialshare.Controllers.Exceptions.EntityNotFoundException;
import com.perpetualtutorial.tutorialshare.Models.EntityServices;
import com.perpetualtutorial.tutorialshare.Models.Tutorial.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//@RestController
//@RequestMapping("/api")
public class Controller<E extends EntityServices<E>, R extends JpaRepository<E, Long>, A extends RepresentationModelAssembler<E, EntityModel<E>>> {
    private final R repository;
    private final A assembler;

    public Controller(R repository, A assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public R getRepository() {
        return repository;
    }

    public A getAssembler() {
        return assembler;
    }

    //=======================CRUD=====================
    //--------------GET--------------
    //---------Root
    @GetMapping("/all")
    public CollectionModel<EntityModel<E>> all() {
        List<EntityModel<E>> modelList = repository.findAll().stream().map(entity -> assembler.toModel(entity)).collect(Collectors.toList());
        return CollectionModel.of(modelList, linkTo(methodOn(Controller.class).all()).withSelfRel());
    }
    //---------Single
    @GetMapping("/{id}")
    public EntityModel<E> one(@PathVariable Long id) {
        E model = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return assembler.toModel(model);
    }
    //--------------POST-------------
    @PostMapping("")
    public ResponseEntity<EntityModel<E>> newEntity(@RequestBody E newEntity) {
        EntityModel<E> entityModel = assembler.toModel(repository.save(newEntity));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())   // Plug in Resr Link
                .body(entityModel);                                                     // HTTP 201: created status msg
    }
    //--------------PUT---------------
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<E>> replaceEntity(@RequestBody E newEntity, @PathVariable Long id) {
        E updatedEntity = repository.findById(id)
                .map(entity -> {
                    entity.update(newEntity);
                    return repository.save(entity);
                }).orElseGet(() -> {
                    newEntity.setId(id);
                    return repository.save(newEntity);
                });
        EntityModel<E> entityModel = assembler.toModel(updatedEntity);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    //--------------DELETE---------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();  // Return HTTP 204: no content response
    }
}
