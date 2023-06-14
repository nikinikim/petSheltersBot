package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class DefaultHandler extends AbstractMessagingHandler{

    public DefaultHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return true;
    }

    @Override
    public void handlerUpdate(Update update) {
        telegramBot.execute(new sendMessage(update.message().chat().id(), "Для старта наберите: /start"))
    }

}
