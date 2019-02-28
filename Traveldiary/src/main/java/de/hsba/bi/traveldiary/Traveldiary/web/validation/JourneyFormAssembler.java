package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyStage;
import org.springframework.stereotype.Component;

@Component
public class JourneyFormAssembler {

    public JourneyForm toForm(Journey journey) {
        JourneyForm form = new JourneyForm();
        form.setName(journey.getName());
        form.setForAll(journey.isForAll());
        return form;
    }

    public Journey update(Journey journey, JourneyForm form) {
        journey.setName(form.getName());
        journey.setForAll(form.isForAll());
        return journey;
    }

    public JourneyStageForm toForm(JourneyStage stage) {
        JourneyStageForm form = new JourneyStageForm();
        form.setName(stage.getName());
        form.setKilometer(stage.getKilometer());
        form.setNotes(stage.getNotes());
        return form;
    }

    public JourneyStage update(JourneyStage stage, JourneyStageForm form) {
        stage.setName(form.getName());
        stage.setKilometer(form.getKilometer());
        stage.setNotes(form.getNotes());
        return stage;
    }
}
