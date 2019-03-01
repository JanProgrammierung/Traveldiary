package de.hsba.bi.traveldiary.Traveldiary.user;

import org.junit.Before;
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
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() {
        //delete the Users created by the UserService's init method
        repository.deleteAll();
        //synchronize session data with database
        repository.flush();
    }

    @Test
    public void shouldWork() {
        //create new Users
        User olaf = new User("Olaf");
        User jonas = new User("Jonas");
        User mathilde = new User("Mathilde");
        repository.save(olaf);
        repository.save(jonas);
        repository.save(mathilde);

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

