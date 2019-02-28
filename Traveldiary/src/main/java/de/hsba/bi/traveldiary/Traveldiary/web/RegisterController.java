package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.user.User;
import de.hsba.bi.traveldiary.Traveldiary.user.UserService;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.RegisterForm;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.RegisterFormAssembler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    private final RegisterFormAssembler registerFormAssembler;

    public RegisterController(UserService userService, RegisterFormAssembler registerFormAssembler) {
        this.userService = userService;
        this.registerFormAssembler = registerFormAssembler;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("registerForm", new RegisterForm());
        return auth instanceof AnonymousAuthenticationToken ? "registration" : "redirect:/";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("registerForm")@Valid RegisterForm registerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        User user = new User();
        this.userService.createUserByEntiy(registerFormAssembler.update(user, registerForm));
        /* try {
            this.userService.createUserByEntiy(formAssembler.update(user, formValidation));
        } catch (Exception e) {

            throw new InternalServerError();
        } */
        return "redirect:/index";
    }
}
