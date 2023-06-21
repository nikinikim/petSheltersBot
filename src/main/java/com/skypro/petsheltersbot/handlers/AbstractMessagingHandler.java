package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;

public abstract class AbstractMessagingHandler implements TelegramHandler {
    protected TelegramBot telegramBot;

    public AbstractMessagingHandler(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

}
