package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.model.Update;

public interface TelegramHandler {

    void  handleUpdate(Update update);

    void handlerUpdatePet(Update update, String petType);

    boolean appliesTo(Update update);



}