package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class NextReportHandler extends AbstractMessagingHandler {
    public NextReportHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/next");
    }

    @Override
    public void handlerUpdate(Update update) {

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }
}
