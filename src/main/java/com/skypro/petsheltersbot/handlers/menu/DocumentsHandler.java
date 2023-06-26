package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DocumentsHandler extends AbstractMessagingHandler {
    public DocumentsHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Паспорт \n\n " +
                        "2. Выписка из трудовой книжки \n\n " +
                        "3. Выписку из домовой книги \n\n " +
                        "4. Выписку с банковского счета \n\n "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления с правилами транспортировки собаки, нажмите /transportirationRules"));

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/documents");
    }
}
