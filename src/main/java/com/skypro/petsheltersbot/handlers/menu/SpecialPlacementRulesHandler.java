package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7)
public class SpecialPlacementRulesHandler extends AbstractMessagingHandler {
    public SpecialPlacementRulesHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }
    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Питомца нужно правильно их кормить \n\n " +
                        "2. Питомцам инвалидам часто требуются специальная " +
                        "гимнастика и массажи, принудительное опорожнение " +
                        "мочевого пузыря и кишечника, профилактика пролежней " +
                        "и другие процедуры. \n\n " +
                        "3. Воспитание, дрессировка и регулярные игры необходимы " +
                        "для питомцев инвалидов точно так же, как и для обычных \n\n " +
                        "4. Если у вас дома уже есть кошка или собака, а вы решили " +
                        "взять животное с особенностями, соблюдайте общие правила " +
                        "их «знакомства» и делайте это постепенно "));
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }
    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/specialPlacementRules");
    }
}
