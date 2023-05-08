package ru.batyrkhanov.springcourse.AlisaBot.model;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.batyrkhanov.springcourse.AlisaBot.repository.UserRepository;

import java.util.Date;
import java.util.TimerTask;

public  class MyTimerTask extends TimerTask {

    @Override
    public void run() {

        System.out.println("TimerTask начал свое выполнение в:" + new Date());
        completeTask();
        System.out.println("TimerTask закончил свое выполнение в:" + new Date());
    }


    private void completeTask() {
        SendMessage message = new SendMessage();
        UserRepository userRepository;


    }
}