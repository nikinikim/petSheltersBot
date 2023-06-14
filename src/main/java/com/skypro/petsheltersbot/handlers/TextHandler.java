package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
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
        telegramBot.execute(new sendMessage(update.message().chat().id(), "Для старта наберите: /start"));
    }
}
