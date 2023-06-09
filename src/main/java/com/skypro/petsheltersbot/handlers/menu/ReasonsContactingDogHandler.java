package com.skypro.petsheltersbot.handlers.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order()
public class ReasonsContactingDogHandler extends AbstractMessagingHandler {
    public ReasonsContactingDogHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }
    @Override
    public void handleUpdate(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "1. Вы впервые решили завести щенка. Консультация кинолога еще до того, как в " +
                        "вашем доме появилась собака, поможет избежать множества проблем с воспитанием собаки. \n\n " +
                        "2. Начальный курс дрессировки «пробуксовывает». Если вы не можете добиться от щенка " +
                        "понимания и послушания, если освоение первоначальных команд идет с трудом, " +
                        "вы выходите из терпения — кинолог поможет вам «найти ключик» к животному \n\n " +
                        "3. Собака слушается вас «под настроение». Команды хозяина должны выполняться " +
                        "беспрекословно — и с первого раза. Если же питомец слушается вас через " +
                        "раз — это сигнал, что вы не справляетесь с выстраиванием взаимоотношений. " +
                        "Своенравность не только осложняет совместную жизнь с собакой: если питомец " +
                        "беспрекословно не повинуется командам во время прогулки без поводка, " +
                        "это может быть опасно для его жизни и здоровья. \n\n " +
                        "4. У животного проблемы во взаимоотношениях с другими собаками. " +
                        "Если питомец, сталкиваясь с собратьями, проявляет агрессию или " +
                        "трусость — это знак того, что пора обращаться к кинологу " +
                        "с запросом на социализацию. \n\n " +
                        "5. Собака пытается завоевать место лидера: пытается занять" +
                        " хозяйское место, требует кормить ее в первую очередь, не повинуется " +
                        "членам семьи. Это значит, что хозяевам пора научиться объяснять, " +
                        "кто здесь главный на понятном собакам языке — и это тоже поможет " +
                        "сделать кинолог.\n\n " +
                        "6. Ваш питомец «хулиганит» дома в ваше отсутствие: портит или " +
                        "разбрасывает вещи, справляет нужду в неположенном месте, заливается " +
                        "лаем или громко скулит. Это тоже отклонение — и тоже знак того, " +
                        "что хозяину и собаки нужно помочь наладить взаимопонимание. \n\n " +
                        "7. Собаке предстоит участие в выставке. В таком случае ей " +
                        "обязательно нужен курс хорошей ринговой дрессировки. И, если " +
                        "хозяин не является опытным собаководом – без помощи профессионала " +
                        "тут не обойтись. Правильно подготовленные собаки как правило " +
                        "оцениваются выше, чем превосходящие их по физическим данным, " +
                        "но не умеющие себя вести животные."));
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Для ознакомления с причинами отказа, нажмите /reasonRefused"));
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/reasonsContactingDogHandler");
    }
}
