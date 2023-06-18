package com.skypro.petsheltersbot.handlers.menu.cat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class PlacementRulesBigCatHandler extends AbstractMessagingHandler {
    public PlacementRulesBigCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null || update.message().text().equals("/placementRulesBig")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Дайте кошке время на адаптацию в новом доме. " +
                        "Если желаете собаке добра, по приезду домой " +
                        "оставьте ее в покое, предоставив свободный " +
                        "доступ к воде и корму. Не навязывайте ей свое " +
                        "общество. Собаке потребуется от нескольких часов " +
                        "до нескольких дней, чтобы прийти в себя и оценить " +
                        "обстановку"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                " Для ознакомления с правилами размещения кошки/котёнка с" +
                        " ограниченными возможностями после приезда из приюта," +
                        " нажмите /specialPlacementRules"));

    }
}
