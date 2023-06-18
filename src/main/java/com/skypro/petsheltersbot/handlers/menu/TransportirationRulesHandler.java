package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class TransportirationRulesHandler extends AbstractMessagingHandler {
    public TransportirationRulesHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null || update.message().text().equals("/transportirationRules")) {
            weight += 2;
        }
        return weight;
    }


    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData("/Menu"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Перевозить собаку из приюта домой лучше в специальном контейнере-переноске. \n\n " +
                        "Некоторые собаки испытывают стресс в автомобиле, особенно если раньше они \n\n " +
                        "на автомобиле не катались. Наличие специальной перевозки сделает поездку \n\n " +
                        "легче и для Вас, и для собаки. Если не хотите покупать себе собственную \n\n " +
                        "перевозку, можете попросить у человека, у которого берете собаку, или у друзей. \n\n" +
                        "Запаситесь дополнительными покрывалами на сидения,  бумажными полотенцами, \n\n " +
                        "спросите в приюте или у волонтеров об особенностях перевозки собаки в машине. \n\n " +
                        "Для ознакомления с правилами размещения маленького питомца ").replyMarkup(keyboardMarkup));

    }
}
