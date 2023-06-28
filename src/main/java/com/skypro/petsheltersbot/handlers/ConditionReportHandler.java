package com.skypro.petsheltersbot.handlers;

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
public class ConditionReportHandler extends AbstractMessagingHandler {
    public ConditionReportHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/Состояние_питомца");
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), """
                Пожалуйста, опишите как чувствует себя ваш питомец на новом месте:
                1. Проявляет ли он агрессию или слишком аппатичный?
                2. Появились ли какие-то болезни?
                3. Часто ли линяет?
                4. Как ведет себя с другими питомцами?
                5. Какие привычки у него повились?
                6. Хорошо ли прошла адаптация к новому дому?                
                
                """));
    }

    @Override
    public void handlerUpdate(Update update)  {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData("/next"));
            telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Для заполнения следующего пункта, нажмите /next", update.message().from().firstName())).replyMarkup(keyboardMarkup));

    }
}
