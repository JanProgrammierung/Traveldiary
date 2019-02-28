package de.hsba.bi.traveldiary.Traveldiary.web;

import javax.validation.constraints.Size;

public class JourneyForm {

    @Size.List({
            @Size(min = 3, message = "Der Name der Reise muss mindestens 3 Zeichen beinhalten"),
            @Size(max = 255, message = "Der Name der Reise darf nicht mehr als 255 Zeichen beinhalten")
    })
    private String name;

    private boolean forAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }
}
