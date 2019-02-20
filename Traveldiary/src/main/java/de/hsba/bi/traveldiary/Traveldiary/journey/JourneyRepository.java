package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    //optional, not necessary; maybe delete at the end if it does not make any sense
    @Query("select distinct j from Journey j join j.stages s where s.name like :pattern")
    List<Journey> findByDescription(@Param("pattern") String pattern);

    // add special Queries
}
