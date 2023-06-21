package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TextHandler extends AbstractMessagingHandler {

    public TextHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && !update.message().text().isBlank();
    }

    @Override
    public void handlerUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Нажмите кнопку /start и выберите приют ('Приют для кошек' или 'Приют для собак')"));
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
    }
}
