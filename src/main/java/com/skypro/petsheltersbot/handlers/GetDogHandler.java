package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class GetDogHandler extends AbstractMessagingHandler {

    private ReasonsContactingDogHandler reasonsContactingDogHandler;
    private ReasonRefusedHandler refusedHandler;

    public GetDogHandler(TelegramBot telegramBot) {
        super(telegramBot);
        new ReasonsContactingDogHandler(telegramBot);
        new ReasonRefusedHandler(telegramBot);

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.callbackQuery().message().chat().id() != null && update.callbackQuery().data().equals("/Menu");
    }

    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства питомцем").callbackData(String.format("/ruleDating")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData(String.format("/documents")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки собаки или щенка").callbackData(String.format("/transportirationRules")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения щенка").callbackData(String.format("/placementRulesLittle")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослой собаки").callbackData(String.format("/placementRulesBig")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения животного c ограниченными возможностям").callbackData(String.format("/specialPlacementRules")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Первые советы кинолога").callbackData(String.format("/firstTipsDogHandler")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Причины обращения к кинологу").callbackData(String.format("/reasonsContactingDogHandler")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData(String.format("/reasonRefused")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData(String.format("/registration")));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/callVolunteer")));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Ознакомьтесь с информацией по усыновлению вашего будущего питомца", update.message().from().firstName())).replyMarkup(keyboardMarkup));

    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства c питомцем").callbackData(String.format("/ruleDatingCat")));
        this.telegramBot.execute(new SendMessage(update.message().from().id(), String.format("Если Вы хотите ознакомиться с правилами знакомства питомцем, нажмите кнопку ниже", update.message().chat().id())).replyMarkup(keyboardMarkup));
    }

    @Order(8)
    public class FirstTipsDogHandler {
        private TelegramBot telegramBot;

        public FirstTipsDogHandler(TelegramBot telegramBot) {
            this.telegramBot = telegramBot;
        }

        public boolean appliesTo(Update update) {
            return update.message().text() != null && update.message().text().equals("/firstTipsDogHandler");
        }

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
    }

    @Order(9)
    public class ReasonsContactingDogHandler {

        private TelegramBot telegramBot;

        public ReasonsContactingDogHandler(TelegramBot telegramBot) {
            this.telegramBot = telegramBot;

        }

        public boolean appliesTo(Update update) {
            return update.message().text() != null && update.message().text().equals("/reasonsContactingDogHandler");
        }

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
                    "Для ознакомления с правилами размещения взрослой кошки" +
                            "после приезда из приюта, нажмите /placementRulesBig"));
        }

    }

    @Order(10)
    public class ReasonRefusedHandler {
        private TelegramBot telegramBot;

        public ReasonRefusedHandler(TelegramBot telegramBot) {
            this.telegramBot = telegramBot;
        }

        public boolean appliesTo(Update update) {
            return update.message().text() != null && update.message().text().equals("/reasonRefusedHandler");
        }

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
    }


}

