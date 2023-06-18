package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.model.Update;

public interface TelegramHandler {

    int getWeight(Update update);

    void  handleUpdate(Update update);


}