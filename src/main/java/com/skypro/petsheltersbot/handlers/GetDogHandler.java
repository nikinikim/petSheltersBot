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


    public GetDogHandler(TelegramBot telegramBot) {
        super(telegramBot);


    }

    private final Map<Long, DogStatus> dogStatus = new HashMap<>();

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.message().text() != null) {
            weight += 1;
        }
        if (update.message().text().equals("/Dog")) {
            weight += 1;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Выберите пункт меню, который Вас интересует"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила знакомства с собакой или щенком, /ruleDating"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Список документов на усыновление, /documents"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила перевозки собаки или щенка, /transportirationRules"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения щенка, /placementRulesLittle"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения взрослой собаки, /placementRulesBig"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения животного c ограниченными возможностями, /specialPlacementRules"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Первые советы кинолога, /firstTipsDogHandler"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Причины обращения к кинологу, /reasonsContactingDogHandler"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Причины отказа, /reasonRefused"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Регистрация, /registration"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Позвать на помощь волонтера, /callVolunteer"));

    }
}