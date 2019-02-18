package de.hsba.bi.traveldiary.Traveldiary.journey;

import java.util.ArrayList;
import java.util.List;

public class Journey {

    private Long id;

    //Add Required!
    private String name;

    private List<JourneyStage> stages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JourneyStage> getStages() {
        if (stages == null) {
            stages = new ArrayList<>();
        }
        return stages;
    }

    public void setStages(List<JourneyStage> stages) {
        this.stages = stages;
    }
}
