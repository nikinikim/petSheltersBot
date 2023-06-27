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
@Order(4)
public class TransportirationRulesHandler extends AbstractMessagingHandler {
    public TransportirationRulesHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }
    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Перевозить питомца из приюта домой лучше в специальном контейнере-переноске. \n\n " +
                        "Некоторые питомцы испытывают стресс в автомобиле, особенно если раньше они \n\n " +
                        "на автомобиле не катались. Наличие специальной перевозки сделает поездку \n\n " +
                        "легче и для Вас, и для питомца. Если не хотите покупать себе собственную \n\n " +
                        "перевозку, можете попросить у человека, у которого берете питомца, или у друзей. \n\n" +
                        "Запаситесь дополнительными покрывалами на сидения,  бумажными полотенцами, \n\n " +
                        "спросите в приюте или у волонтеров об особенностях перевозки питомца в машине."));

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/transportirationRules");
    }
}
