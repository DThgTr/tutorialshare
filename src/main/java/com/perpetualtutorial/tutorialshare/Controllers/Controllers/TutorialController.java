package com.perpetualtutorial.tutorialshare.Controllers.Controllers;

import com.perpetualtutorial.tutorialshare.Controllers.Controller;
import com.perpetualtutorial.tutorialshare.Controllers.Controllers.TutorialModelAssembler;
import com.perpetualtutorial.tutorialshare.Models.Tutorial.Tutorial;
import com.perpetualtutorial.tutorialshare.Repositories.TutorialRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tutorials")
public class TutorialController extends Controller<Tutorial, TutorialRepository, TutorialModelAssembler> {
    public TutorialController(TutorialRepository repository, TutorialModelAssembler assembler) {
        super(repository, assembler);
    }
}

