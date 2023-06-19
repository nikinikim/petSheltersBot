package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.petsheltersbot.entity.Volunteer;
import com.skypro.petsheltersbot.reposetory.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;
@Component
public class VolunteerRequestHandler {

    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerRequestHandler(TelegramBot telegramBot, VolunteerRepository volunteerRepository) {
        this.telegramBot = telegramBot;
        this.volunteerRepository = volunteerRepository;
    }

    public void handleVolunteerRequest(long chatId) {
        String message = "Спасибо за ваше обращение! Мы уже связываемся с нашим волонтером.";

        sendMessage(chatId, message);
    }

    public void handleNewVolunteer(long chatId, String username, String phoneNumber) {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(username);
        volunteer.setUsername(username);
        volunteer.setPhoneNumber(phoneNumber);
        volunteerRepository.save(volunteer);

        String response = "Вы успешно зарегистрировались как волонтер! Мы свяжемся с вами в ближайшее время.";
        sendMessage(chatId, response);
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {

        }
    }
}


