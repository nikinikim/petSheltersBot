package com.skypro.petsheltersbot.handlers.report;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import liquibase.util.StringUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class ReportHandler extends AbstractMessagingHandler {
    public ReportHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/report");
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        if (!StringUtil.isEmpty(petType)) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить фото питомца").callbackData(String.format("/photo")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить рацион питомца").callbackData(String.format("/Рацион")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчет об общем самочувстие питомца").callbackData(String.format("/Состояние_питомца")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/Волонтер")));
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), String.format("Выберите направление, нажав на кнопку ниже:"/*, update.message().from().firstName()*/)).replyMarkup(keyboardMarkup));
        }
    }

    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчет о питомце").callbackData(String.format("/report")));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Хотите отправить отчет?", update.message().from().firstName())).replyMarkup(keyboardMarkup));
    }
}
