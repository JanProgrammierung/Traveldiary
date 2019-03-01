package de.hsba.bi.traveldiary.Traveldiary.journey;

import javax.persistence.*;

@Entity
public class JourneyStage {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Journey journey;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private Double kilometer;

    //optional
    private String notes;

    //Default Constructor
    public JourneyStage() {
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKilometer() {
        return kilometer;
    }

    public void setKilometer(Double kilometer) {
        this.kilometer = kilometer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }


}
