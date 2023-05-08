package ru.batyrkhanov.springcourse.AlisaBot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.batyrkhanov.springcourse.AlisaBot.model.Message;
import ru.batyrkhanov.springcourse.AlisaBot.model.User;
import ru.batyrkhanov.springcourse.AlisaBot.repository.MessageRepository;

@Service
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void save(Message message) {
        messageRepository.save(message);
    }
}
