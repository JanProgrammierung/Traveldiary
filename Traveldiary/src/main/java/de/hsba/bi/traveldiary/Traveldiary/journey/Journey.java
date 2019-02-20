package de.hsba.bi.traveldiary.Traveldiary.journey;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Journey {

    @Id
    @GeneratedValue
    private Long id;

    //add not optional
    @Basic(optional = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "journey")
    @OrderBy
    private List<JourneyStage> stages;

    public Journey() {
    }

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
}
