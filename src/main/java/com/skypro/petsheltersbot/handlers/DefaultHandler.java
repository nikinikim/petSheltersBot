package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class DefaultHandler extends AbstractMessagingHandler {

    public DefaultHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return true;
    }

    @Override
    public void handlerUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Для продолжения нажмите /start"));
    }

    /*@Override
    public void handlerUpdateCat(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Для продолжения нажмите кнопку Кошки или Собаки"));
    }

    @Override
    public void handlerUpdateDog(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Для продолжения нажмите кнопку Кошки или Собаки"));
    }*/

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), "Для продолжения нажмите кнопку "));
    }
}
