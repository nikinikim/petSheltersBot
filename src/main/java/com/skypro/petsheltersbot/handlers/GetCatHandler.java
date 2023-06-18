package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.CatStatus;
import com.skypro.petsheltersbot.service.CatUserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetCatHandler extends AbstractMessagingHandler {


    public GetCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    private final Map<Long, CatStatus> catStatus = new HashMap<>();

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.message().text() != null) {
            weight += 1;
        }
        if (update.message().text().equals("/Cat")) {
            weight += 1;
        }
        return weight;
    }


    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Выберите пункт меню, который Вас интересует"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила знакомства с кошкой или котенком, /ruleDating"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Список документов на усыновление, /documents"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила перевозки кошки или котенка, /transportirationRules"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения котенка, /placementRulesLittle"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения взрослой кошки, /placementRulesBig"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Правила размещения животного c ограниченными возможностями, /specialPlacementRules"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Причины отказа, /reasonRefused"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Регистрация, /registration"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Позвать на помощь волонтера, /callVolunteer"));

    }
}

