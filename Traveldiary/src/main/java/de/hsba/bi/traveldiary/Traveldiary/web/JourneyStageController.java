package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyStage;
import de.hsba.bi.traveldiary.Traveldiary.web.exception.ForbiddenException;
import de.hsba.bi.traveldiary.Traveldiary.web.exception.NotFoundException;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.FirstJourneyStageForm;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyFormAssembler;
import de.hsba.bi.traveldiary.Traveldiary.web.validation.JourneyStageForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/stages/{id}")
public class JourneyStageController {

    private final JourneyService journeyService;
    private final JourneyFormAssembler journeyFormAssembler;

    //Constructor
    public JourneyStageController(JourneyService journeyService, JourneyFormAssembler journeyFormAssembler) {
        this.journeyService = journeyService;
        this.journeyFormAssembler = journeyFormAssembler;
    }

    @ModelAttribute("stage")
    public JourneyStage getStage(@PathVariable("id") Long id) {
        JourneyStage stage = journeyService.findStage(id);
        if (stage == null) {
            throw new NotFoundException();
        }
        return stage;
    }

    //Opens the stage page, but only if the journey of the stage is owned by the User
    @GetMapping
    public String show(Model model, @PathVariable("id") Long id) {
        JourneyStage stage = getStage(id);
        if (stage.getJourney().isOwnedByCurrentUser()) {
            if (stage.getJourney().getStages().indexOf(stage) == 0) {
                model.addAttribute("stageForm", journeyFormAssembler.toFormFirst(getStage(id)));
            } else {
                model.addAttribute("stageForm", journeyFormAssembler.toForm(getStage(id)));
            }
                return "journeys/stage";
        }
        //no UnauthorizedException needed, because the Edit Stages pages are never public
         else throw new ForbiddenException();
    }

    //Updates the stage
    @PostMapping
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("stageForm") @Valid JourneyStageForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/stage";
        }
        JourneyStage stage = getStage(id);
        journeyService.save(journeyFormAssembler.update(stage, form));
        return "redirect:/journeys/" + stage.getJourney().getId();
    }

    //Updates the first stage
    @PostMapping(path = "/first")
    public String updateFirst(
            @PathVariable("id") Long id,
            @ModelAttribute("stageForm") @Valid FirstJourneyStageForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/stage";
        }
        JourneyStage stage = getStage(id);
        journeyService.save(journeyFormAssembler.updateFirst(stage, form));
        return "redirect:/journeys/" + stage.getJourney().getId();
    }

    //Deletes the stage
    @PostMapping(path = "/delete")
    @PreAuthorize("authenticated")
    public String delete(@PathVariable("id") Long id) {
        JourneyStage stage = getStage(id);
        journeyService.deleteStage(id);
        return "redirect:/journeys/" + stage.getJourney().getId();
    }
}
