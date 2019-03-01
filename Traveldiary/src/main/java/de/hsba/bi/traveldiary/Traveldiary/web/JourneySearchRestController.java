package de.hsba.bi.traveldiary.Traveldiary.web;

import de.hsba.bi.traveldiary.Traveldiary.journey.Journey;
import de.hsba.bi.traveldiary.Traveldiary.journey.JourneyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JourneySearchRestController {

    //Inner static class
    public static class JourneySearchResult {

        private String name;
        private String link;

        JourneySearchResult(String name, String link) {
            this.name = name;
            this.link = link;
        }

        public String getName() {
            return name;
        }
    }

    private final JourneyService journeyService;

    //Constructor
    public JourneySearchRestController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    //Searches for the required journeys
    @GetMapping("/journeys/search")
    public List<JourneySearchResult> performSearch(@RequestParam(value = "search", required = false) String search) {
        Collection<Journey> journeys = journeyService.findJourneys(search);
        List<JourneySearchResult> results = buildResult(journeys);
        return results;
    }

    //Lambda expressions to build the search result
    private List<JourneySearchResult> buildResult(Collection<Journey> journeys) {
        return journeys.stream()
                .map(journey -> new JourneySearchResult(journey.getName(), "/journeys/" + journey.getId()))
                .collect(Collectors.toList());
    }
}
