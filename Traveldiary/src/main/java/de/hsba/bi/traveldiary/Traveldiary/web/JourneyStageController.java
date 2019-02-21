package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyStage;
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

    @GetMapping
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("stageForm", journeyFormAssembler.toForm(getStage(id)));
        return "journeys/stage";
    }

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
}
