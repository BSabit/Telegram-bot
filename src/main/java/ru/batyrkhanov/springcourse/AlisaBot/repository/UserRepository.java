package ru.batyrkhanov.springcourse.AlisaBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.batyrkhanov.springcourse.AlisaBot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(long chatId);
}
