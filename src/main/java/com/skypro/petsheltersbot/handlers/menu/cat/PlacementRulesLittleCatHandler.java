package com.skypro.petsheltersbot.handlers.menu.cat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class PlacementRulesLittleCatHandler extends AbstractMessagingHandler {
    public PlacementRulesLittleCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null || update.message().text().equals("/placementRulesLittle")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Перед тем как взять кошку/котёнка из приюта," +
                        "обязательно выясните, все ли прививки сделали кошке/котёнку \n\n " +
                        "2. Не организовывайте для кошки/котёнка лишь одно место, " +
                        "где ей позволено спать и лежать. \n\n " +
                        "3. Оборудуйте один из укромных уголков для кошки/котёнка у вашей кровати \n\n " +
                        "4. Старайтесь побольше общаться с питомцем \n\n " +
                        "5. Дайте щенку побольше игрушек "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления с правилами размещения взрослой кошки" +
                        "после приезда из приюта, нажмите /placementRulesBig"));

    }
}
