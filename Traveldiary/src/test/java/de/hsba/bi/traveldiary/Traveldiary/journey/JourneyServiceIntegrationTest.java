package de.hsba.bi.traveldiary.Traveldiary.journey;

import de.hsba.bi.traveldiary.Traveldiary.user.User;
import de.hsba.bi.traveldiary.Traveldiary.user.UserAdapter;
import de.hsba.bi.traveldiary.Traveldiary.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JourneyServiceIntegrationTest {

    @Autowired
    private JourneyService service;

    @Autowired
    private UserRepository userRepository;

    //create User in order to create journeys / for the test to function correctly
    private User testUser = new User("Test", "password");

    @Before
    public void setUp() {
        userRepository.save(testUser);
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(new UserAdapter(testUser), null));
    }

    @Test
    public void shouldWork() {
        //create and save 3 journeys
        Journey japan = createJourney("Japan");
        Journey argentina = createJourney("Argentina");
        Journey australia = createJourney("Australia");

        //create and save 3 stages
        JourneyStage tokyo = createJourneyStage("Tokyo");
        JourneyStage cordoba = createJourneyStage("Cordoba");
        JourneyStage melbourne = createJourneyStage("Melbourne");

        //add the stages to the journeys
        service.addJourneyStage(japan, tokyo);
        service.addJourneyStage(argentina, cordoba);
        service.addJourneyStage(australia, melbourne);



        //test getJourney method
        assertEquals(japan, service.getJourney(japan.getId()));
        assertEquals(argentina, service.getJourney(argentina.getId()));
        assertEquals(australia, service.getJourney(australia.getId()));

        //test findJourneys method (includes findByStage query)
        assertTrue(service.findJourneys("Tokyo").contains(japan));
        assertTrue(service.findJourneys("Cordoba").contains(argentina));
        assertTrue(service.findJourneys("Melbourne").contains(australia));

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

    //method in order to create a new journey and set the required fields
    private Journey createJourney(String name) {
        Journey journey = new Journey();
        journey.setName(name);
        //WARNING: Security Risk; only enable the setOwner method for JourneyIntegrationTest! Go to Journey class and uncomment the method
        journey.setOwner(testUser);
        journey.setForAll(true);
        return service.save(journey);
    }

    //method in order to create a new journey and set the required fields
    private JourneyStage createJourneyStage(String name) {
        JourneyStage journeyStage = new JourneyStage();
        journeyStage.setName(name);
        journeyStage.setKilometer(1.00);
        return service.save(journeyStage);
    }
}
