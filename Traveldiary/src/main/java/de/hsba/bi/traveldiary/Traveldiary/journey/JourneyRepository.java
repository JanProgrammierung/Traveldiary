package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    // add special Queries
}
