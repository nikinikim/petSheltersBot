package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.stereotype.Component;

@Component
public class PlacementRulesLittleHandler extends AbstractMessagingHandler {
    public PlacementRulesLittleHandler(TelegramBot telegramBot) {
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
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData("/Menu"));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Перед тем как взять щенка из приюта," +
                "обязательно выясните, все ли прививки сделали питомцу \n\n " +
                        "2. Не организовывайте для щенка лишь одно место, " +
                        "где ему позволено спать и лежать. \n\n " +
                        "3. Оборудуйте один из укромных уголков для щенка у вашей кровати \n\n " +
                        "4. Если в приюте у щенка была своя подстилка, " +
                        "принесите кусочек этой подстилки в новый дом \n\n " +
                        "5. Постарайтесь с первых дней разъяснять юному подопечному правила \n\n " +
                        "6. Старайтесь не перегружать щенка излишней физической активностью \n\n " +
                        "7. Старайтесь побольше общаться со щенком \n\n " +
                        "8. Дайте щенку побольше игрушек \n\n " +
                        "9. Общий курс дрессировки можно начинать примерно через пару месяцев \n\n ").replyMarkup(keyboardMarkup));

    }
}
