package com.perpetualtutorial.tutorialshare.template;

import com.perpetualtutorial.tutorialshare.utils.Exceptions.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Service
@Primary
public class EntityServices<E extends DataModelTemplate, R extends JpaRepository<E, Long>> {
    private final R repository;
    public EntityServices(R repository) {
        this.repository = repository;
    }
    public List<E> findAll() {
        return repository.findAll();
    }
    public E findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
    public E save(E newEntity) {
        return repository.save(newEntity);
    }
     public E update(E updatedEntity, Long id) {
        return repository.findById(id)
                .map(entity -> {
                    return repository.save(updatedEntity);
                }).orElseGet(() -> {
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                });
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
