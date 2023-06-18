package com.skypro.petsheltersbot.handlers.menu.cat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class DocumentsCatHandler extends AbstractMessagingHandler {
    public DocumentsCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null || update.message().text().equals("/documents")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Паспорт \n\n " +
                        "2. Выписка из трудовой книжки \n\n " +
                        "3. Выписку из домовой книги \n\n " +
                        "4. Выписку с банковского счета \n\n "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления с правилами транспортировки питомца, нажмите /transportirationRules"));

    }

}
