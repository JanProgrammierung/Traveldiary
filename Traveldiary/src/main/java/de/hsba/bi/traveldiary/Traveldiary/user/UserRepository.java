package de.hsba.bi.traveldiary.Traveldiary.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//WARNING: Security Risk; only make public for JourneyIntegrationTest!
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Query to return User via name
    @Query("select u from User u where u.name = :name")
    User findByName(@Param("name") String name);

    //Query to return all User
    @Query("select u from User u")
    List<User> findUsers();
}
