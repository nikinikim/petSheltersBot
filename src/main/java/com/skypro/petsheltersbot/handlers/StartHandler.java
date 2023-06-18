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
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/Информация_о_приюте"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес, схема проезда").callbackData("/CONTACTINFO"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/REGISTRATIONOFCARPASS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData("/SAFETYSHELTER"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("/LEAVECONTACTDETAILS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData("/Позвать_волонтера"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Привет, %s!", update.message().from().firstName())).replyMarkup(keyboardMarkup));
    }

//    public void sendMessage(Long chatId, String message) {
//        telegramBot.execute(new SendMessage(chatId, message));
//    }
}
