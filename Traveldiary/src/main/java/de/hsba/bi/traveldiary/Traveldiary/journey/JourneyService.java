package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JourneyService {

    private final JourneyRepository repository;

    public JourneyService(JourneyRepository repository) {
        this.repository = repository;
    }

    public Journey createJourney(String name) {
        Journey journey = new Journey();
        journey.setName(name);
        return repository.save(journey);
    }

    public Journey getJourney(Long id) {
        return repository.getOne(id);
    }

    public void addJourneyStage(Journey journey, JourneyStage stage) {
        journey.getEntries().add(stage);
    }

    public Collection<Journey> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
