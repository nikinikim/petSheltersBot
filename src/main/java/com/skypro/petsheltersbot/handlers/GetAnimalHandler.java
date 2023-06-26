package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(17)
public class GetAnimalHandler extends AbstractMessagingHandler {
    public GetAnimalHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Выберите питомца, которого Вы предпочитаете!"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "/Cat"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "/Dog"));

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/Menu");
    }
}






