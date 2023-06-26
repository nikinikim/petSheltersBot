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
@Order(6)
public class PlacementRulesBigHandler extends AbstractMessagingHandler {
    public PlacementRulesBigHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }


    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Дайте питомцу время на адаптацию в новом доме. " +
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

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/placementRulesBig");
    }
}
