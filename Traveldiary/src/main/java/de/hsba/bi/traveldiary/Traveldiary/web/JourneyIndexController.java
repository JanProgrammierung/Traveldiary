package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
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

    public JourneyIndexController(JourneyService journeyService, JourneyFormAssembler journeyFormAssembler) {
        this.journeyService = journeyService;
        this.journeyFormAssembler = journeyFormAssembler;
    }

    @ModelAttribute("journeys")
    public Collection<Journey> journeys(@RequestParam(value = "suche", required = false) String search) {
        return journeyService.findJourneys(search);
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "suche", required = false) String search) {
        model.addAttribute("suche", search);
        model.addAttribute("journeyForm", new JourneyForm());
        return "journeys/index";
    }

    @PostMapping
    public String create(@ModelAttribute("journeyForm") @Valid JourneyForm journeyForm, BindingResult journeyBinding) {
        if (journeyBinding.hasErrors()) {
            return "journeys/index";
        }
        Journey journey = journeyService.save(journeyFormAssembler.update(new Journey(), journeyForm));
        return "redirect:/journeys/" + journey.getId();
    }


}
