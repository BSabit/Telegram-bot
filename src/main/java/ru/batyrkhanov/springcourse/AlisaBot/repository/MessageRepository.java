package ru.batyrkhanov.springcourse.AlisaBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.batyrkhanov.springcourse.AlisaBot.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
