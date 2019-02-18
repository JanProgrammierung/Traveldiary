package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyStage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("journeys", journeyService.getAll());
        return "journeys/index";
    }

    @PostMapping
    public String create(String name) {
        Journey journal = journeyService.createJourney(name);
        return "redirect:/journeys/" + journal.getId();
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("journey", journeyService.getJourney(id));
        return "journeys/show";
    }

    @PostMapping(path = "/{id}")
    public String addStage(@PathVariable("id") Long id, JourneyStage stage) {
        Journey journey = journeyService.getJourney(id);
        journeyService.addJourneyStage(journey, stage);
        return "redirect:/journeys/" + id;
    }

    @PostMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        journeyService.delete(id);
        return "redirect:/journeys/";
    }
}
