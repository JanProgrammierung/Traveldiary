package de.hsba.bi.traveldiary.Traveldiary.journey;

public class JourneyStage {

    private String name;

    private Double kilometer;

    //optional
    //evtl. Liste?
    //Min Length einfügen
    private String notes;

    public JourneyStage() {
    }

    public JourneyStage(String name, Double kilometer, String notes) {
        this.name = name;
        this.kilometer = kilometer;
        this.notes = notes;
    }

    //nötig, weil Notizen optional sind?
    public JourneyStage(String name, Double kilometer) {
        this.name = name;
        this.kilometer = kilometer;
    }

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
}
