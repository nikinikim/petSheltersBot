package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.petsheltersbot.entity.Volunteer;
import com.skypro.petsheltersbot.listener.TelegramBotUpdatesListener;
import com.skypro.petsheltersbot.reposetory.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;
@Service
public class VolunteerRequestHandler {
    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;

    public VolunteerRequestHandler(TelegramBot telegramBot, VolunteerRepository volunteerRepository) {
        this.telegramBot = telegramBot;
        this.volunteerRepository = volunteerRepository;
    }

    public void handleVolunteerRequest(long chatId) {
        String message = "Спасибо за ваше обращение! Мы уже связываемся с нашим волонтером.";

        SendMessage request = new SendMessage(chatId, message);
        telegramBot.execute(request);

        Volunteer volunteer = getRandomVolunteer();

        if (volunteer != null) {
            String volunteerMessage = "У вас новый запрос на помощь!\n\n"
                    + "Пожалуйста, свяжитесь с пользователем для предоставления необходимой помощи.";

            SendMessage volunteerRequest = new SendMessage(volunteer.getUsername(), volunteerMessage);
            telegramBot.execute(volunteerRequest);
        }
    }

    public void processUserQuestion(long chatId, String question) {
        Volunteer volunteer = getRandomVolunteer();

        if (volunteer != null) {
            String volunteerMessage = "Вам поступил вопрос от пользователя:\n\n"
                    + question
                    + "\n\nПожалуйста, ответите на него.";

            SendMessage volunteerResponse = new SendMessage(volunteer.getUsername(), volunteerMessage);
            telegramBot.execute(volunteerResponse);
        } else {
            String errorMessage = "К сожалению, в данный момент нет доступных волонтеров. Пожалуйста, попробуйте позже.";
            SendMessage errorResponse = new SendMessage(chatId, errorMessage);
            telegramBot.execute(errorResponse);
        }
    }

    private Volunteer getRandomVolunteer() {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (!volunteers.isEmpty()) {
            int randomIndex = new Random().nextInt(volunteers.size());
            return volunteers.get(randomIndex);
        }
        return null;
    }

    public void processVolunteerData(String name, String username, String phoneNumber) {
        Volunteer volunteer = new Volunteer(name, username, phoneNumber);
        volunteerRepository.save(volunteer);

        String successMessage = "Спасибо за вашу регистрацию в качестве волонтера! Теперь вы можете помогать нам.";
        SendMessage response = new SendMessage(username, successMessage);
        telegramBot.execute(response);
    }
}
