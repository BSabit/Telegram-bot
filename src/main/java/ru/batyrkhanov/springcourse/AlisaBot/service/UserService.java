package ru.batyrkhanov.springcourse.AlisaBot.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.batyrkhanov.springcourse.AlisaBot.model.User;
import ru.batyrkhanov.springcourse.AlisaBot.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
