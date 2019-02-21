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

        public String getLink() {
            return link;
        }
    }

    private final JourneyService journeyService;

    public JourneySearchRestController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping("/journeys/search")
    public List<JourneySearchResult> performSearch(@RequestParam(value = "search", required = false) String search) {
        Collection<Journey> journeys = journeyService.findJourneys(search);
        List<JourneySearchResult> results = buildResult(journeys);
        return results;
    }

    private List<JourneySearchResult> buildResult(Collection<Journey> journeys) {
        return journeys.stream()
                .map(journey -> new JourneySearchResult(journey.getName(), "/journeys/" + journey.getId()))
                .collect(Collectors.toList());
    }
}
