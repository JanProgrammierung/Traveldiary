package de.hsba.bi.traveldiary.Traveldiary.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //Constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Delete before submitting
    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            createUser("JanL", "jan123");
            createUser("Random", "password");
        }
    }

    public void createUserByEntity(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private void createUser(String username, String password) {
        userRepository.save(new User(username, passwordEncoder.encode(password)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }
}
