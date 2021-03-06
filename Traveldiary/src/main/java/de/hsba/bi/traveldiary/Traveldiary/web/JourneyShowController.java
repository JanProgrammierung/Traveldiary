package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyStage;
import de.hsba.bi.traveldiary.Traveldiary.user.User;
import de.hsba.bi.traveldiary.Traveldiary.web.exception.ForbiddenException;
import de.hsba.bi.traveldiary.Traveldiary.web.exception.NotFoundException;
import de.hsba.bi.traveldiary.Traveldiary.web.exception.UnauthorizedException;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.FirstJourneyStageForm;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyForm;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyFormAssembler;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyStageForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/journeys/{id}")
public class JourneyShowController {

    private final JourneyService journeyService;

    private final JourneyFormAssembler journeyFormAssembler;

    //Constructor
    public JourneyShowController(JourneyService journeyService, JourneyFormAssembler journeyFormAssembler) {
        this.journeyService = journeyService;
        this.journeyFormAssembler = journeyFormAssembler;
    }

    @ModelAttribute("journey")
    public Journey getJourney(@PathVariable("id") Long id) {
        Journey journey = journeyService.getJourney(id);
        if (journey == null) {
            throw new NotFoundException();
        }
        return journey;
    }

    //Opens journey show page if journey is public or it's the journey owner
    @GetMapping
    public String show(@PathVariable("id") Long id, Model model) {
        Journey journey = getJourney(id);
        if (journey.isOwnedByCurrentUser() || journey.isForAll()) {
            model.addAttribute("journeyForm", journeyFormAssembler.toForm(journey));
            if (!journey.getStages().isEmpty()) {
                model.addAttribute("journeyStageForm", new JourneyStageForm());
            } else {
                model.addAttribute("firstJourneyStageForm", new FirstJourneyStageForm());
            }
            return "journeys/show";
        } else if (User.getCurrentUser() == null) {
                throw new UnauthorizedException();
        } else throw new ForbiddenException();
    }

    //Changes the name of the Journey or make/unmake it public
    @PostMapping
    @PreAuthorize("authenticated")
    public String change(Model model, @PathVariable("id") Long id,
                         @ModelAttribute("journeyForm") @Valid JourneyForm journeyForm, BindingResult journeyBinding) {
        if (journeyBinding.hasErrors()) {
            model.addAttribute("journeyStageForm", new JourneyStage());
            return "journeys/show";
        }
        journeyService.save(journeyFormAssembler.update(getJourney(id), journeyForm));
        return "redirect:/journeys/" + id;
    }

    //Adds the first stage to the journey
    @PostMapping(path = "/firststage")
    @PreAuthorize("authenticated")
    public String addFirstStage(Model model, @PathVariable("id") Long id,
                           @ModelAttribute("firstJourneyStageForm") @Valid FirstJourneyStageForm stageForm, BindingResult stageBinding) {
        Journey journey = getJourney(id);
        if (stageBinding.hasErrors()) {
            model.addAttribute("journeyForm", journeyFormAssembler.toForm(journey));
            return "journeys/show";
        }
        journeyService.addJourneyStage(journey, journeyFormAssembler.updateFirst(new JourneyStage(), stageForm));
        return "redirect:/journeys/" + id;
    }

    //Adds a new stage to the journey
    @PostMapping(path = "/stages")
    @PreAuthorize("authenticated")
    public String addStage(Model model, @PathVariable("id") Long id,
                           @ModelAttribute("journeyStageForm") @Valid JourneyStageForm stageForm, BindingResult stageBinding) {
        Journey journey = getJourney(id);
        if (stageBinding.hasErrors()) {
            model.addAttribute("journeyForm", journeyFormAssembler.toForm(journey));
            return "journeys/show";
        }
        journeyService.addJourneyStage(journey, journeyFormAssembler.update(new JourneyStage(), stageForm));
        return "redirect:/journeys/" + id;
    }

    //Deletes the journey
    @PostMapping(path = "/delete")
    @PreAuthorize("authenticated")
    public String delete(@PathVariable("id") Long id) {
        journeyService.delete(id);
        return "redirect:/journeys/";
    }
}
