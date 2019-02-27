package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.user.User;

import javax.validation.constraints.Size;

public class RegisterForm {

    @Size.List({
            @Size(min = 4, message = "Der Benutzername muss mindestens 4 Zeichen beinhalten."),
            @Size(max = 30, message = "Der Benutzername darf nicht mehr als 30 Zeichen beinhalten.")
    })
    private String name;


    @Size.List({
            @Size(min = 6, message = "Das Passwort muss mindestens 6 Zeichen beinhalten."),
            @Size(max = 30, message = "Das Passwort darf nicht mehr als 30 Zeichen beinhalten.")
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
