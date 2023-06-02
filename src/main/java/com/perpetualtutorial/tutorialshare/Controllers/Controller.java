package com.perpetualtutorial.tutorialshare.Controllers;

import com.perpetualtutorial.tutorialshare.Controllers.Exceptions.EntityNotFoundException;
import com.perpetualtutorial.tutorialshare.Models.EntityServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class Controller<E extends EntityServices<E>, R extends JpaRepository<E, Long>> {
    private final R repository;
    private final ModelAssembler<E> assembler;
    private final String rootLink;

    public Controller(R repository, ModelAssembler<E> assembler, String rootLink) {
        this.repository = repository;
        this.assembler = assembler;
        this.rootLink = rootLink;
    }
    //=======================CRUD=====================
    //--------------GET--------------
    //---------Root
    @GetMapping("/all")
    CollectionModel<EntityModel<E>> all() {
        List<EntityModel<E>> modelList = repository.findAll().stream().map(entity -> toModel(entity, rootLink)).collect(Collectors.toList());
        return CollectionModel.of(modelList, linkTo(methodOn(Controller.class).all()).withSelfRel());
    }
    //---------Single
    @GetMapping("/{id}")
    EntityModel<E> one(@PathVariable Long id) {
        E model = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return assembler.toModel(model, rootLink);
    }
    //--------------POST-------------
    @PostMapping("/")
    ResponseEntity<EntityModel<E>> newEntity(@RequestBody E newEntity) {
        EntityModel<E> entityModel = assembler.toModel(repository.save(newEntity), rootLink);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())   // Plug in Resr Link
                .body(entityModel);                                                     // HTTP 201: created status msg
    }
    //--------------PUT---------------
    @PutMapping("/{id}")
    ResponseEntity<EntityModel<E>> replaceEntity(@RequestBody E newEntity, @PathVariable Long id) {
        E updatedEntity = repository.findById(id)
                .map(entity -> {
                    entity.update(newEntity);
                    return repository.save(entity);
                }).orElseGet(() -> {
                    newEntity.setId(id);
                    return repository.save(newEntity);
                });
        EntityModel<E> entityModel = assembler.toModel(updatedEntity, rootLink);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    //--------------DELETE---------------
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();  // Return HTTP 204: no content response
    }
}
