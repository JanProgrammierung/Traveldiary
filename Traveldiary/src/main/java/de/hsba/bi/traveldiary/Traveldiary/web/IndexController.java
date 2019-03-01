package de.hsba.bi.traveldiary.Traveldiary.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    //redirects to the journey index page if someone enters no path
    @GetMapping
    public String index() {
        return "redirect:/journeys/";
    }

    //redirects to the journey index page if someone enters /index
    @GetMapping("/index")
    public String linkIndex() {
        return "redirect:/journeys/";
    }

    //gets to the login page only if the user is not logged in yet
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }
}
