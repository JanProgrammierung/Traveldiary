package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
@Transactional
public class JourneyService {

    private final JourneyRepository repository;
    private final JourneyStageRepository stageRepository;

    //Constructor
    public JourneyService(JourneyRepository repository, JourneyStageRepository stageRepository) {
        this.repository = repository;
        this.stageRepository = stageRepository;
    }

    public Journey save(Journey journey) {
        return repository.save(journey);
    }

    public Journey getJourney(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addJourneyStage(Journey journey, JourneyStage stage) {
        stage.setJourney(journey);
        journey.getStages().add(stage);
    }

    //Users can search for a distinct Stage name
    public Collection<Journey> findJourneys(String search) {
        return StringUtils.hasText(search) ? repository.findByStage("%" + search + "%") : repository.findAll();
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public void deleteStage(Long id) {
        this.stageRepository.deleteById(id);
    }

    public JourneyStage findStage(Long id) {
        return stageRepository.findById(id).orElse(null);
    }

    public JourneyStage save(JourneyStage stage) {
        return stageRepository.save(stage);
    }

    //Calculates the kilometer sum of all journeys of a User
    public double computeTotalKilometers() {
        double totalKilometers = 0.00;
        for (Journey journey : findJourneys("")) {
            if (journey.isOwnedByCurrentUser()) {
                totalKilometers += journey.computeKilometers();
            }
        }
        return totalKilometers;
    }
}
