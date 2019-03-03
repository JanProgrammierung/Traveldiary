package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import javax.validation.constraints.Size;

public class JourneyForm {

    @Size.List({
            @Size(min = 3, message = "{validation.name.notTooShort}"),
            @Size(max = 255, message = "{validation.name.notTooLong}")
    })
    private String name;

    private boolean forAll;

    private String description;

    //Getter and Setter
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
