package com.perpetualtutorial.tutorialshare.Models;

public interface EntityServices<E> {
    Long getId();
    void setId(Long id);
    void update(E updatedEntity);
}
