package com.perpetualtutorial.tutorialshare.feature.Tutorial;

import com.perpetualtutorial.tutorialshare.template.EntityServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("TutorialService")
public class TutorialService extends EntityServices<Tutorial, TutorialRepository> {

    public TutorialService(TutorialRepository repository) {
        super(repository);
    }
}
