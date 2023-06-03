package com.perpetualtutorial.tutorialshare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpetualtutorial.tutorialshare.Models.Tutorial.*;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
