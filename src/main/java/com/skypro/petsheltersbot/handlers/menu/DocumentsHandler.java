package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.stereotype.Component;

@Component
public class DocumentsHandler extends AbstractMessagingHandler {
    public DocumentsHandler(TelegramBot telegramBot) {
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
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData("/Menu"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Паспорт \n\n " +
                        "2. Выписка из трудовой книжки \n\n " +
                        "3. Выписку из домовой книги \n\n " +
                        "4. Выписку с банковского счета \n\n ").replyMarkup(keyboardMarkup));

    }
}
