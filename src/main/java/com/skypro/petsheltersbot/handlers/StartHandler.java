package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class StartHandler extends AbstractMessagingHandler {

    public StartHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/start");
    }

    @Override
    public void handlerUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/Информация о приюте"),
                new InlineKeyboardButton("Расписание работы приюта, адрес, схема проезда").callbackData("/Расписание работы приюта, адрес, схема проезда"),
                new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/Контактные данные охраны для оформления пропуска на машину"),
                new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData("/Общие рекомендации о технике безопасности на территории приюта"),
                new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("/Оставить контактные данные для связи"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("/Позвать волонтера"));
//        String text = update.message().text();
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Привет, %s!", update.message().from().firstName())).replyMarkup(keyboardMarkup));
    }

//    public void sendMessage(Long chatId, String message) {
//        telegramBot.execute(new SendMessage(chatId, message));
//    }
}
