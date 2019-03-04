package de.hsba.bi.traveldiary.Traveldiary.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository repository;

    //create Users in order to create journeys / for the test to function correctly
    User olaf = new User("Olaf", "olaf");
    User jonas = new User("Jonas", "jonas");
    User mathilde = new User("Mathilde", "mathilde");

    @Before
    public void setUp() {
        repository.save(olaf);
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(new UserAdapter(olaf), null));
        repository.save(jonas);
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(new UserAdapter(jonas), null));
        repository.save(mathilde);
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(new UserAdapter(mathilde), null));
    }

    @Test
    public void shouldWork() {
        //test findByName query method
        assertEquals(olaf, repository.findByName("Olaf"));
        assertEquals(jonas, repository.findByName("Jonas"));
        assertEquals(mathilde, repository.findByName("Mathilde"));
        assertNull(repository.findByName("Thomas"));
        assertNull(repository.findByName("Jürgen"));
        assertNull(repository.findByName("Pascal"));

        //test findUsers query method
        assertTrue(repository.findUsers().contains(olaf));
        assertTrue(repository.findUsers().contains(jonas));
        assertTrue(repository.findUsers().contains(mathilde));
    }
}

