package com.skypro.petsheltersbot.handlers.report;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class IntakeReportHandler extends AbstractMessagingHandler {
    public IntakeReportHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/Рацион");
    }

    @Override
    public void handlerUpdatePet(Update update, String petType){
        telegramBot.execute(new SendMessage(update.message().chat().id(), """
                Пожалуйста, опишите рацион вашего питомца: 
                натуральная еда или специальный сухой/жидкий корм(
                название корма, который вы используете) и сколько грамм в день.
                """));
    }

    @Override
    public void handleUpdate(Update update)  {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData("/next"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Для заполнения следующего пункта, нажмите /next", update.message().from().firstName())).replyMarkup(keyboardMarkup));

    }
}