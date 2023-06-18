package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAnimalHandler extends AbstractMessagingHandler {
    public GetAnimalHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.message().text() != null) {
            weight += 1;
        }
        if (update.message().text() != null && update.message().text().equals("/Menu")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(
                new InlineKeyboardButton("Кошка").callbackData("/Cat"),
                new InlineKeyboardButton("Собака").callbackData("/Dog"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Выберите питомца, которого Вы предпочитаете!").replyMarkup(keyboardMarkup));
        CallbackQuery callbackQuery = update.callbackQuery();
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("Собака")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Выберите питомца, которого Вы предпочитаете!"));
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("Собака");
                inlineKeyboardButton.callbackData("Выберите пункт меню, который Вас интересует");
                List<InlineKeyboardButton> buttonList = new ArrayList<>();
                buttonList.add(new InlineKeyboardButton("Правила знакомства с собакой или щенком").callbackData("/ruleDating"));
            }

        }
    }
}





