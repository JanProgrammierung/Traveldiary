package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import de.hsba.bi.traveldiary.Traveldiary.user.User;

import javax.validation.constraints.Size;

public class RegisterForm {

    @Size.List({
            @Size(min = 4, message = "{validation.username.notTooShort}"),
            @Size(max = 30, message = "{validation.username.notTooLong}")
    })
    private String name;


    @Size.List({
            @Size(min = 6, message = "{validation.password.notTooShort}"),
            @Size(max = 30, message = "{validation.password.notTooLong}")
    })
    private String password;

    public RegisterForm() {
    }

    public RegisterForm(User user) {
        user.setName(getName());
        user.setPassword(getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
