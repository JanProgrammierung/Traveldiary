package de.hsba.bi.traveldiary.Traveldiary.web;

import javax.validation.constraints.Size;

public class JourneyForm {

    @Size.List({
            @Size(min = 3, message = "Das Ziel der Etappe muss mindestens 3 Zeichen beinhalten"),
            @Size(max = 255, message = "Das Ziel der Etappe darf nicht mehr als 255 Zeichen beinhalten")
    })
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //with trim() to avoid errors in terms with the @Size annotation because otherwise blank space is considered too
        this.name = name;
    }
}
