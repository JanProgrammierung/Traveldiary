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

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            createUser("admin", "admin", "ADMIN");
            createUser("Anne", "123456", "USER");
            createUser("Benedikt", "987654", "USER");
            createUser("Charlotte", "password", "USER");
            createUser("Mats", "password", "USER");
            createUser("Nina", "password", "USER");
            createUser("Ole", "password", "USER");
            createUser("Xenia", "password", "USER");
            createUser("Yves", "password", "USER");
            createUser("Zoe", "password", "USER");
        }
    }

    private void createUser(String username, String password, String role) {
        userRepository.save(new User(username, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }
}
