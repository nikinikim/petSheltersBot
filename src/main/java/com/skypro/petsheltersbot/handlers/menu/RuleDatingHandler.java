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
@Order(2)
public class RuleDatingHandler extends AbstractMessagingHandler {
    public RuleDatingHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/ruleDating");
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Подробно расспросите сотрудников приюта о питомце \n\n " +
                        "2. Понаблюдайте за поведением питомца в вольере или на прогулке с волонтёром \n\n " +
                        "3. Сфотографируйте питомца или сделайте видео и покажите членам семьи \n\n " +
                        "4. Покажите собаку специалисту-кинологу, если вы собираетесь взять собаку, или знакомому с опытом содержания кошек, если собираетесь взять кошку \n\n " +
                        "5. Познакомьтесь с питомцем, пока он находится в приюте \n\n " +
                        "6. Проведите первичный осмотр питомца в приюте \n\n " +
                        "7. Попробуйте поконтактиоовать с питомцем в присутствии волонтёра\n\n " +
                        "8. Постарайтесь привезти питомца к себе домой в гости "));

    }
    @Override
    public void handlerUpdatePet(Update update, String petType) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData("/documents"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Чтобы ознакомиться со списком документов для усыновления питомца, нажмите на кнопку ниже:", update.message().from().firstName())).replyMarkup(keyboardMarkup));
    }

}

