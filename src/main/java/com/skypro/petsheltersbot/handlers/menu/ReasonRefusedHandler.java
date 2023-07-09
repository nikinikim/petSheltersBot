package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order()
public class ReasonRefusedHandler extends AbstractMessagingHandler {
    public ReasonRefusedHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Отказ обеспечить безопасность питомца на новом месте \n\n " +
                        "2. Нестабильные отношения в семье \n\n " +
                        "3. Антинаучное мышление \n\n " +
                        "4. Маленькие дети в семье \n\n " +
                        "5. Аллергия \n\n " +
                        "6. Животное забирают в подарок кому-то \n\n " +
                        "7. Животное забирают в целях использования его рабочих качеств \n\n " +
                        "8. Отказ приехать познакомиться с животным \n\n " +
                        "9. Претендент — пожилой человек, проживающий один \n\n " +
                        "10. Отсутствие регистрации и собственного жилья или " +
                        "его несоответствие нормам приюта \n\n " +
                        "11. Без объяснения причин \n\n "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Если Вы хотите зарегистрироваться на нашем сайте, нажмите /registration"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Все интересующие вопросы Вы можете задать нашим волонтерам, нажав /callVolunteer"));

    }
    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/reasonRefused");
    }
}
