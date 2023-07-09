package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.configuration.CatStatus;
import com.skypro.petsheltersbot.service.CatUserService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(2)
public class GetCatHandler extends AbstractMessagingHandler {

    public GetCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.callbackQuery() != null && update.callbackQuery().data().equals("/Menu");
    }


    @Override
    public void handleUpdate(Update update) {
        String data = update.callbackQuery().data();
        if (data.equals("/Menu")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства питомцем").callbackData(String.format("/ruleDatingCat")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData(String.format("/documents")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки кошки или котенка").callbackData(String.format("/transportirationRulesCat")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения котенка").callbackData(String.format("/placementRulesLittleCat")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослой кошки").callbackData(String.format("/placementRulesBigCat")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения животного c ограниченными возможностям").callbackData(String.format("/specialPlacementRulesCat")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData(String.format("/reasonRefused")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData(String.format("/registration")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/callVolunteer")));
            this.telegramBot.execute(new SendMessage(update.message().from().id(), String.format("Внимательно ознакомьтесь с информацией по усыновлению кошки", update.message().chat().id())).replyMarkup(keyboardMarkup));
        }
    }

    @Override    public void handlerUpdatePet(Update update, String petType) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства c питомцем").callbackData(String.format("/ruleDatingCat")));
        this.telegramBot.execute(new SendMessage(update.message().from().id(), String.format("Если Вы хотите ознакомиться с правилами знакомства питомцем, нажмите кнопку ниже", update.message().chat().id())).replyMarkup(keyboardMarkup));
    }

}



