package com.skypro.petsheltersbot.handlers.menu.cat;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.stereotype.Component;

@Component
public class RuleDatingCatHandler extends AbstractMessagingHandler {
    public RuleDatingCatHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.callbackQuery() != null) {
            weight += 1;
        }
        if (update.callbackQuery() != null || update.message().text().equals("/ruleDating")) {
            weight += 2;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Подробно расспросите сотрудников приюта о кошке/котёнке \n\n " +
                        "2. Понаблюдайте за поведением кошки/котёнка в вольере \n\n " +
                        "3. Сфотографируйте кошку/котёнка или сделайте видео и покажите членам семьи \n\n " +
                        "4. Покажите кошку/котёнка ветеринару или знакомому с опытом содержания кошек \n\n " +
                        "5. Познакомьтесь с кошкой/котёнком, пока она находится в приюте \n\n " +
                        "6. Проведите первичный осмотр кошки/котёнка в приюте \n\n " +
                        "7. Подержите на руках или побудьте рядом с кошкой/котёнком в присутствии волонтёра \n\n " +
                        "8. Постарайтесь привезти кошку/котёнка к себе домой в гости " ));
                        telegramBot.execute(new SendMessage(update.message().chat().id(),
                                "Для ознакомления со списком документов по усыновлению кошки/котёнка, нажмите /documents"));
    }
}


