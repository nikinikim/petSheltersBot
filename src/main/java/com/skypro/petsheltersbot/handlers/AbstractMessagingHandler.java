package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.skypro.petsheltersbot.handlers.TelegramHandler;

public abstract class AbstractMessagingHandler implements TelegramHandler {
    protected TelegramBot telegramBot;

    public AbstractMessagingHandler(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

}