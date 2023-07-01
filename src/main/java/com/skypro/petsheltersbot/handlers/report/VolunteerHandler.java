package com.skypro.petsheltersbot.handlers.report;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class VolunteerHandler  extends AbstractMessagingHandler {
    public VolunteerHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/Волонтер");
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Позвать волонтера"));
    }

    @Override
    public void handleUpdate(Update update)  {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData("/next"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Для заполнения следующего пункта, нажмите /next", update.message().from().firstName())).replyMarkup(keyboardMarkup));

    }
}
