package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.petsheltersbot.handlers.VolunteerRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final VolunteerRequestHandler volunteerRequestHandler;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, VolunteerRequestHandler volunteerRequestHandler) {
        this.telegramBot = telegramBot;
        this.volunteerRequestHandler = volunteerRequestHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        logger.info("Handles update: {}", update);
                        Message message = update.message();
                        Long chatId = message.chat().id();
                        String text = message.text();

                        if ("/start".equals(text)) {
                            volunteerRequestHandler.handleVolunteerRequest(chatId);
                        } else if ("/add_volunteer".equals(text)) {
                            // Отправляем сообщение с запросом данных о волонтере
                            SendMessage requestMessage = new SendMessage(chatId, "Пожалуйста, отправьте свои данные: имя, никнейм и номер телефона.");
                            telegramBot.execute(requestMessage);
                        } else {
                            volunteerRequestHandler.processUserQuestion(chatId, text);
                        }
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}