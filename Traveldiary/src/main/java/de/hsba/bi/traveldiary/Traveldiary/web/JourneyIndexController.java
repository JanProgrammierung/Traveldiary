package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyForm;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyFormAssembler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/journeys")
public class JourneyIndexController {

    private final JourneyService journeyService;

    private final JourneyFormAssembler journeyFormAssembler;

    //Constructor
    public JourneyIndexController(JourneyService journeyService, JourneyFormAssembler journeyFormAssembler) {
        this.journeyService = journeyService;
        this.journeyFormAssembler = journeyFormAssembler;
    }

    //journeys model attribute gets connected to the search parameter
    @ModelAttribute("journeys")
    public Collection<Journey> journeys(@RequestParam(value = "suche", required = false) String search) {
        return journeyService.findJourneys(search);
    }

    //totalKilometers model attribute is the kilometer sum of all journeys of a user
    @ModelAttribute("totalKilometers")
    public double totalKilometers() {
        double totalKilometers = 0.00;
        for (Journey journey : journeyService.findJourneys("")) {
            if (journey.isOwnedByCurrentUser()) {
                totalKilometers += journey.computeKilometers();
            }
        }
        return totalKilometers;
    }

    //Opens the index page
    @GetMapping
    public String index(Model model, @RequestParam(value = "suche", required = false) String search) {
        model.addAttribute("suche", search);
        model.addAttribute("journeyForm", new JourneyForm());
        return "journeys/index";
    }

    //Creates a new journal (only if user is logged in)
    @PostMapping
    @PreAuthorize("authenticated")
    public String create(@ModelAttribute("journeyForm") @Valid JourneyForm journeyForm, BindingResult journeyBinding) {
        if (journeyBinding.hasErrors()) {
            return "journeys/index";
        }
        Journey journey = journeyService.save(journeyFormAssembler.update(new Journey(), journeyForm));
        return "redirect:/journeys/" + journey.getId();
    }


}
