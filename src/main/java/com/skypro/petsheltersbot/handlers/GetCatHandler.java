package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.configuration.CatStatus;
import com.skypro.petsheltersbot.service.CatUserService;
import liquibase.util.StringUtil;
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
        return update.callbackQuery().message().text() != null && update.callbackQuery().message().text().equals("/Menu");
    }

    /**
     * Ответ на запрос "Как взять кошку из приюта"
     *
     * @param update
     */

    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства питомцем").callbackData(String.format("/ruleDating")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData(String.format("/documents")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки кошки или котенка").callbackData(String.format("/transportirationRules")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения котенка").callbackData(String.format("/placementRulesLittle")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослой кошки").callbackData(String.format("/placementRulesBig")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения животного c ограниченными возможностям").callbackData(String.format("/specialPlacementRules")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData(String.format("/reasonRefused")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData(String.format("/registration")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/callVolunteer")));
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), String.format("Внимательно ознакомьтесь с информацией по усыновлению кошки")).replyMarkup(keyboardMarkup));
    }

    /**
     * Создание кнопки выхода из меню
     *
     * @param update
     * @param petType
     */

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        if (!StringUtil.isEmpty(petType)) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"));
            telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Вернуться к выбору приюта, нажмите кнопку ниже", update.message().from().firstName())).replyMarkup(keyboardMarkup));
        }

    }

}



