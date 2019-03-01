package de.hsba.bi.traveldiary.Traveldiary.journey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JourneyServiceIntegrationTest {

    @Autowired
    private JourneyService service;

    @Test
    public void shouldWork() {
        //erstellen und speichern von 3 Reisen
        Journey japan = new Journey();
        Journey argentina = new Journey();
        Journey australia = new Journey();
        japan.setName("Japan");
        argentina.setName("Argentina");
        australia.setName("Australia");
        service.save(japan);
        service.save(argentina);
        service.save(australia);

        //erstellen und speichern von 3 Etappen
        JourneyStage tokyo = new JourneyStage();
        JourneyStage cordoba = new JourneyStage();
        JourneyStage melbourne = new JourneyStage();
        service.save(tokyo);
        service.save(cordoba);
        service.save(melbourne);

        //zusammenfügen von Reisen und Etappen
        service.addJourneyStage(japan, tokyo);
        service.addJourneyStage(argentina, cordoba);
        service.addJourneyStage(australia, melbourne);



        //test getJourney method
        assertEquals(japan, service.getJourney(japan.getId()));
        assertEquals(argentina, service.getJourney(argentina.getId()));
        assertEquals(australia, service.getJourney(australia.getId()));

        //test findJourneys method (includes findByStage query)
        assertTrue(service.findJourneys("Japan").contains(japan));
        assertTrue(service.findJourneys("Argentina").contains(argentina));
        assertTrue(service.findJourneys("Australia").contains(australia));

        //test findStage method
        assertEquals(tokyo, service.findStage(tokyo.getId()));
        assertEquals(cordoba, service.findStage(cordoba.getId()));
        assertEquals(melbourne, service.findStage(melbourne.getId()));

        //delete journeys
        service.delete(japan.getId());
        service.delete(argentina.getId());
        service.delete(australia.getId());

        //test if journeys are deleted
        assertNull(service.getJourney(japan.getId()));
        assertNull(service.getJourney(argentina.getId()));
        assertNull(service.getJourney(australia.getId()));

    }
}
