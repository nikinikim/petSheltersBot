package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.model.Update;

public interface TelegramHandler {
    boolean appliesTo(Update update);

    void handlerUpdate(Update update);
}
