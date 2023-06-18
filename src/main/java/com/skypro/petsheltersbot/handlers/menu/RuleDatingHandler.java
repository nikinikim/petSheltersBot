package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.stereotype.Component;

@Component
public class RuleDatingHandler extends AbstractMessagingHandler {
    public RuleDatingHandler(TelegramBot telegramBot) {
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
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData("/Menu"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Подробно расспросите сотрудников приюта о собаке \n\n " +
                        "2. Понаблюдайте за поведением собаки в вольере или на прогулке с волонтёром \n\n " +
                        "3. Сфотографируйте собаку или сделайте видео и покажите членам семьи \n\n " +
                        "4. Покажите собаку специалисту-кинологу или знакомому собаководу \n\n " +
                        "5. Познакомьтесь с собакой, пока она находится в приюте \n\n " +
                        "6. Проведите первичный осмотр собаки в приюте \n\n " +
                        "7. Погуляйте с собакой в присутствии волонтёра \n\n " +
                        "8. Постарайтесь привезти собаку к себе домой в гости ").replyMarkup(keyboardMarkup));

    }
}
