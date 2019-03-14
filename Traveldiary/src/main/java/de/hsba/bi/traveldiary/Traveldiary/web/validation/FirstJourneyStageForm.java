package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FirstJourneyStageForm {

    @Size.List({
            @Size(min = 3, message = "{validation.stagename.notTooShort}"),
            @Size(max = 255, message = "{validation.stagename.notTooLong}")
    })
    private String name;

    //Default set to 0.00 because if the first stage is added to the journey, kilometer is always 0
    //Except for the starting stage, kilometer is always >= 0.1 (see JourneyStageForm)
    @NotNull(message = "{validation.kilometer.notEmpty}")
    private Double kilometer = 0.00;

    private String notes;

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKilometer() {
        return kilometer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
