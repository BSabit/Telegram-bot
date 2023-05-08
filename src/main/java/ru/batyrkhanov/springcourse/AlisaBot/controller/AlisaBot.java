package ru.batyrkhanov.springcourse.AlisaBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.batyrkhanov.springcourse.AlisaBot.config.BotConfig;
import ru.batyrkhanov.springcourse.AlisaBot.model.Message;
import ru.batyrkhanov.springcourse.AlisaBot.model.MyTimerTask;
import ru.batyrkhanov.springcourse.AlisaBot.model.User;
import ru.batyrkhanov.springcourse.AlisaBot.repository.UserRepository;
import ru.batyrkhanov.springcourse.AlisaBot.service.MessageService;
import ru.batyrkhanov.springcourse.AlisaBot.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Component
public class AlisaBot extends TelegramLongPollingBot {

     private final UserRepository userRepository;
     private final MessageService messageService;
    final BotConfig config;


    @Autowired
    public AlisaBot(UserRepository userRepository, MessageService messageService, BotConfig config) {

        this.userRepository = userRepository;
        this.messageService = messageService;
        this.config = config;
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {


            long chatId = update.getMessage().getChatId();

            String name = update.getMessage().getChat().getFirstName();

            User user = userRepository.findById(chatId);

            Message messageText = new Message(update.getMessage().getText());




            // TODO check if user exists and create if not
            if (user == null) {
                System.out.println("User not found");
                user = new User(chatId, name);
                user.setMessages(new ArrayList<>());
                userRepository.save(user);

                messageText.setOwner(user);
                messageService.save(messageText);

            }

            // TODO save message
            messageText.setOwner(user);
            messageService.save(messageText);

            // TODO reply to message ...
            startCommandReceived(chatId, name);


            //TODO timer hourly

            TimerTask timerTask = new MyTimerTask();
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
            System.out.println("Таймер запущен");

        }
    }


    private void  startCommandReceived(long chatId, String name) {
        String answer = "Привет, " +name + ", рада с тобой познакомиться!";

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException ignored) {
        }

    }

    private void userAndMessagesEachHour() {
        for (User u : userRepository.findAll()) {
            sendMessage(u.getId(), u.getName());
            for (Message message : u.getMessages()) {
                sendMessage(u.getId(), message.getMessage());
            }
        }
    }

}
