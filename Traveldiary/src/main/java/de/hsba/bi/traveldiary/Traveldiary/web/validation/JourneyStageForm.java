package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JourneyStageForm {

    @Size.List({
            @Size(min = 3, message = "{validation.stagename.notTooShort}"),
            @Size(max = 255, message = "{validation.stagename.notTooLong}")
    })
    private String name;

    //Except for the starting stage, kilometer is always >= 0.1
    @DecimalMin(value = "0.1", message = "{validation.kilometer.notTooShort}")
    @NotNull(message = "{validation.kilometer.notEmpty}")
    private Double kilometer;

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
