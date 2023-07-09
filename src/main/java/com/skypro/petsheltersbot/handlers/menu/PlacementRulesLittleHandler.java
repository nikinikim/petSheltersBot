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
@Order(5)
public class PlacementRulesLittleHandler extends AbstractMessagingHandler {
    public PlacementRulesLittleHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }
    @Override
    public void handlerUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Перед тем как взять маленького питомца," +
                        "обязательно выясните, все ли прививки сделали питомцу \n\n " +
                        "2. Не организовывайте для питомца лишь одно место, " +
                        "где ему позволено спать и лежать. \n\n " +
                        "3. Оборудуйте один из укромных уголков для питомца у вашей кровати \n\n " +
                        "4. Если в приюте у питомца была своя подстилка, " +
                        "принесите кусочек этой подстилки в новый дом \n\n " +
                        "5. Постарайтесь с первых дней разъяснять юному подопечному правила \n\n " +
                        "6. Старайтесь не перегружать питомца излишней физической активностью \n\n " +
                        "7. Старайтесь побольше общаться с питомцем \n\n " +
                        "8. Дайте питомцу побольше игрушек \n\n "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления с правилами размещения взрослой собаки" +
                        "после приезда из приюта, нажмите /placementRulesBig"));

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/placementRulesLittle");
    }
}