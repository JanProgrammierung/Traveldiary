package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
@Transactional
public class JourneyService {

    private final JourneyRepository repository;

    //check at the end whether you might delete some functions because there might be a chance that you do not use them

    public JourneyService(JourneyRepository repository) {
        this.repository = repository;
    }

    public Journey save(Journey journey) {
        return repository.save(journey);
    }

    public Journey createJourney(String name) {
        Journey journey = new Journey();
        journey.setName(name);
        return repository.save(journey);
    }

    public Journey getJourney(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addJourneyStage(Journey journey, JourneyStage stage) {
        stage.setJourney(journey);
        journey.getStages().add(stage);
    }

    public Collection<Journey> getAll() {
        return repository.findAll();
    }

    public Collection<Journey> findJourneys(String search) {
        return StringUtils.hasText(search) ? repository.findByDescription("%" + search + "%") : repository.findAll();
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
