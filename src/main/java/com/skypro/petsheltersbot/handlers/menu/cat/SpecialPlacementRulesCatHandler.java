package com.skypro.petsheltersbot.handlers.menu.cat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;

public class SpecialPlacementRulesCatHandler extends AbstractMessagingHandler {
    public SpecialPlacementRulesCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {

        int weight = 0;
        if (update.message().text() != null) {
            weight += 1;
        }
        if (update.message().text() != null || update.message().text().equals("/specialPlacementRules")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Кошку нужно правильно их кормить \n\n " +
                        "2. Кошкам инвалидам часто требуются специальная " +
                        "гимнастика и массажи, принудительное опорожнение " +
                        "мочевого пузыря и кишечника, профилактика пролежней " +
                        "и другие процедуры. \n\n " +
                        "3. Воспитание и регулярные игры необходимы " +
                        "для кошек инвалидов точно так же, как и для обычных \n\n " +
                        "4. Если у вас дома уже есть кошка, а вы решили " +
                        "взять животное с особенностями, соблюдайте общие правила " +
                        "их «знакомства» и делайте это постепенно "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для получения информации о причинах отказа отдавать кошку/котёнка, " +
                        "нажмите /firstTipsDogHandler"));
    }
}
