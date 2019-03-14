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
        form.setDescription(journey.getDescription());
        return form;
    }

    public Journey update(Journey journey, JourneyForm form) {
        journey.setName(form.getName());
        journey.setForAll(form.isForAll());
        journey.setDescription(form.getDescription());
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

    public FirstJourneyStageForm toFormFirst(JourneyStage stage) {
        FirstJourneyStageForm form = new FirstJourneyStageForm();
        form.setName(stage.getName());
        form.setNotes(stage.getNotes());
        return form;
    }

    public JourneyStage updateFirst(JourneyStage stage, FirstJourneyStageForm form) {
        stage.setName(form.getName());
        stage.setKilometer(form.getKilometer());
        stage.setNotes(form.getNotes());
        return stage;
    }
}
