package de.hsba.bi.traveldiary.Traveldiary.web;

import javax.validation.constraints.NotBlank;

public class JourneyForm {

    @NotBlank(message = "Sie müssen einen Namen für Ihre Reise eintragen")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
