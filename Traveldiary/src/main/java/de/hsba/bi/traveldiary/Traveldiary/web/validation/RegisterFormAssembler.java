package de.hsba.bi.traveldiary.Traveldiary.web.validation;

import de.hsba.bi.traveldiary.Traveldiary.user.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterFormAssembler {

    public RegisterForm toForm(User user){
        RegisterForm registerForm = new RegisterForm(user);
        return registerForm;
    }

    public User update(User user, RegisterForm registerForm){
        user.setName(registerForm.getName());
        user.setPassword(registerForm.getPassword());
        return user;
    }
}
