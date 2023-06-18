package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.DogStatus;
import com.skypro.petsheltersbot.service.DogUserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetDogHandler extends AbstractMessagingHandler {


    public GetDogHandler(TelegramBot telegramBot, DogUserService dogService) {
        super(telegramBot);


    }

    private final Map<Long, DogStatus> dogStatus = new HashMap<>();

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/Dog"))
                weight += 1;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("Собака");
        inlineKeyboardButton.callbackData("Выберите пункт меню, который Вас интересует");
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        buttonList.add(new InlineKeyboardButton("Правила знакомства с собакой или щенком").callbackData("/ruleDating"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData("/documents"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки собаки или щенка").callbackData("/transportirationRules"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения щенка").callbackData("/placementRulesLittle"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослой собаки").callbackData("/placementRulesBig"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения животного c ограниченными возможностями").callbackData("/specialPlacementRules"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Первые советы кинолога").callbackData("/firstTipsDogHandler"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Причины обращения к кинологу").callbackData("/reasonsContactingDogHandler"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData("/reasonRefused"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData("/registration"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать на помощь волонтера").callbackData("/callVolunteer"));


    }
}