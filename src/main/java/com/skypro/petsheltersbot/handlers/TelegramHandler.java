package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.model.Update;

public interface TelegramHandler {
    boolean appliesTo(Update update);

    void handleUpdate(Update update);

    void handlerUpdatePet(Update update, String petType);

}
