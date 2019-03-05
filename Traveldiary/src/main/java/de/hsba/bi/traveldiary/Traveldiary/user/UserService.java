package de.hsba.bi.traveldiary.Traveldiary.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User createUser(User user) {
        String username = user.getName();
        String password = user.getPassword();
        return userRepository.save(new User(username, passwordEncoder.encode(password)));
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

    //Check if User already exists (for registration)
    public boolean existsUserName (String name) {
        if (userRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }
}
