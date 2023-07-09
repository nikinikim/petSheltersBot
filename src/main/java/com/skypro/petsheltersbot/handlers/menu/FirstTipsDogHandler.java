package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order()
public class FirstTipsDogHandler extends AbstractMessagingHandler {
    public FirstTipsDogHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }
    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/firstTipsDogHandler");
    }
    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Не жалейте награды.Наградой для собаки может быть вкусное лакомство, " +
                        "игры или совместная деятельность. Не жадничайте \n\n " +
                        "2. никогда не наказывайте собаку. Примените отрицательное " +
                        "подкрепление — это неприятное воздействие на собаку " +
                        "непосредственно в тот момент, когда она совершает запретное действие. " +
                        "Тогда она прекрасно поймет, что именно привело к такому результату. \n\n " +
                        "3. научитесь понимать язык тела собаки.Собаки показывают так называемые " +
                        "сигналы примирения — это порой трудноуловимые телодвижения, которые в " +
                        "конкретной ситуации несут важный смысл. \n\n " +
                        "4.  правильно хвалите собаку.  " +
                        "Об этом вам сможет рассказать только ваша собственная собака. " +
                        "Всем нравится по-разному: в разных местах, с разной силой, " +
                        "продолжительностью и интенсивностью. \n\n " +
                        "5. будьте последовательны.Собака легко приспособится к любым " +
                        "правилам. Главное, чтобы они были неизменны. Иначе она никак " +
                        "не сможет предугадать ваше поведение \n\n " +
                        "6. будьте доступными для собаки. Не игнорируйте собаку, " +
                        "если она подошла к вам восстановить эмоциональную связь. "));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления со списком документов по усыновлению кошки/котёнка, нажмите /documents"));
    }
    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

}


