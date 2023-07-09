package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.configuration.CatStatus;
import com.skypro.petsheltersbot.configuration.DogStatus;
import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
import com.skypro.petsheltersbot.repositories.CatUserRepository;
import com.skypro.petsheltersbot.repositories.DogUserRepositiry;
import com.skypro.petsheltersbot.repositories.InfoMessageRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private final InfoMessageRepository infoMessageRepository;
    private final List<TelegramHandler> handlers;
    private final DogUserRepositiry dogUserRepositiry;
    private final CatUserRepository catUserRepository;

    @Autowired
    public TelegramBotUpdatesListener(List<TelegramHandler> handlers, TelegramBot telegramBot, InfoMessageRepository infoMessageRepository, DogUserRepositiry dogUserRepositiry, CatUserRepository catUserRepository) {
        this.handlers = handlers;
        this.telegramBot = telegramBot;
        this.infoMessageRepository = infoMessageRepository;
        this.dogUserRepositiry = dogUserRepositiry;
        this.catUserRepository = catUserRepository;
        this.telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод process вызывается при получении новых обновлений от Telegram Bot API.
     * Метод обрабатывает каждое обновление, фильтруя только те, которые содержат сообщения или callback-запросы,
     * и передает их на обработку методу handleUpdate.
     *
     * @return CONFIRMED_UPDATES_ALL, если обновления были успешно обработаны
     */

    @Override
    public int process(List<Update> updates) {
        updates.stream()
                .filter(update -> update.message() != null || update.callbackQuery() != null)
                .forEach(this::handleUpdate);
        return CONFIRMED_UPDATES_ALL;
    }
    /**
     * Метод init вызывается после создания объекта и регистрирует текущий объект в качестве слушателя обновлений у объекта telegramBot.
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод handleUpdate перебирает список telegramHandlers и вызывает метод appliesTo для каждого обработчика,
     * чтобы определить, какой обработчик должен быть использован для обработки данного обновления.
     * Затем метод вызывает метод handleUpdate для выбранного обработчика.
     *
     * @param update объект, представляющий входящее сообщение
     */

    public void handleUpdate(Update update) {
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/Cats") || data.equals("/Dogs")) {
                for (TelegramHandler handler : handlers) {
                    handler.handlerUpdatePet(update, data);
                    break;
                }
            } else {
                handlerUpdatePet(data, update.callbackQuery().from().id());
            }
        } else {
            for (TelegramHandler handler : handlers) {
                if (handler.appliesTo(update)) {
                    handler.handlerUpdate(update);
                    break;
                } else {
                    handler.handlerUpdatePet(update, "");
                }
            }

        }
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/registration")) {
                for (TelegramHandler handler : handlers) {
                    handler.handlerUpdatePet(update, data);
                    Message text = update.callbackQuery().message();
                    User telegramUser = text.from();
                    CatUser catUser = findOrSaveCatUser(telegramUser);
                    break;

                }
            }
        }
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/registration")) {
                for (TelegramHandler handler : handlers) {
                    handler.handlerUpdatePet(update, data);
                    Message text = update.callbackQuery().message();
                    User telegramUser = text.from();
                    DogUser dogUser = findOrSaveDogUser(telegramUser);
                    break;
                }
            }
        }
    }

    private void handlerUpdatePet(String data, Long fromId) {
        if (data.contains("/Информация_о_приюте")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Приют для %s", data.startsWith("/Cats") ? "кошек. Основан..." : "собак. Основан...")));
        }
        if (data.contains("/Menu")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства питомцем").callbackData(String.format("/ruleDating")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData(String.format("/documents")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки питомца").callbackData(String.format("/transportirationRules")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения маленького питомца").callbackData(String.format("/placementRulesLittle")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослого питомца").callbackData(String.format("/placementRulesBig")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения питомца c ограниченными возможностями").callbackData(String.format("/specialPlacementRules")));
            if (data.contains("/Dogs")) {
                keyboardMarkup.addRow(new InlineKeyboardButton("Первые советы кинолога").callbackData(String.format("/firstTipsDogHandler")));
                keyboardMarkup.addRow(new InlineKeyboardButton("Причины обращения к кинологу").callbackData(String.format("/reasonsContactingDogHandler")));

            }

            keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData(String.format("/reasonRefused")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData(String.format("/registration")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/callVolunteer")));
            telegramBot.execute(new SendMessage(fromId, String.format("Ознакомьтесь с информацией по усыновлению вашего будущего питомца", fromId)).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/ruleDating")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Список документов на усыновление").callbackData(String.format("/documents")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Подробно расспросите сотрудников приюта о питомце \n\n " +
                            "2. Понаблюдайте за поведением питомца в вольере или на прогулке с волонтёром \n\n " +
                            "3. Сфотографируйте питомца или сделайте видео и покажите членам семьи \n\n " +
                            "4. Покажите собаку специалисту-кинологу, если вы собираетесь взять собаку, или знакомому с опытом содержания кошек, если собираетесь взять кошку \n\n " +
                            "5. Познакомьтесь с питомцем, пока он находится в приюте \n\n " +
                            "6. Проведите первичный осмотр питомца в приюте \n\n " +
                            "7. Попробуйте поконтактиоовать с питомцем в присутствии волонтёра\n\n " +
                            "8. Постарайтесь привезти питомца к себе домой в гости ",
                    data.contains("/ruleDating"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/documents")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила перевозки питомца").callbackData(String.format("/transportirationRules")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Паспорт \n\n " +
                            "2. Выписка из трудовой книжки \n\n " +
                            "3. Выписку из домовой книги \n\n " +
                            "4. Выписку с банковского счета \n\n ",
                    data.contains("/documents"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/transportirationRules")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения маленького питомца").callbackData(String.format("/placementRulesLittle")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("Перевозить питомца из приюта домой лучше в специальном контейнере-переноске. \n\n " +
                            "Некоторые питомцы испытывают стресс в автомобиле, особенно если раньше они \n\n " +
                            "на автомобиле не катались. Наличие специальной перевозки сделает поездку \n\n " +
                            "легче и для Вас, и для питомца. Если не хотите покупать себе собственную \n\n " +
                            "перевозку, можете попросить у человека, у которого берете питомца, или у друзей. \n\n" +
                            "Запаситесь дополнительными покрывалами на сидения,  бумажными полотенцами, \n\n " +
                            "спросите в приюте или у волонтеров об особенностях перевозки питомца в машине.",
                    data.contains("/transportirationRules"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/placementRulesLittle")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения взрослого питомца").callbackData(String.format("/placementRulesBig")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Перед тем как взять маленького питомца," +
                            "обязательно выясните, все ли прививки сделали питомцу \n\n " +
                            "2. Не организовывайте для питомца лишь одно место, " +
                            "где ему позволено спать и лежать. \n\n " +
                            "3. Оборудуйте один из укромных уголков для питомца у вашей кровати \n\n " +
                            "4. Если в приюте у питомца была своя подстилка, " +
                            "принесите кусочек этой подстилки в новый дом \n\n " +
                            "5. Постарайтесь с первых дней разъяснять юному подопечному правила \n\n " +
                            "6. Старайтесь не перегружать питомца излишней физической активностью \n\n " +
                            "7. Старайтесь побольше общаться с питомцем \n\n " +
                            "8. Дайте питомцу побольше игрушек \n\n ",
                    data.contains("/placementRulesLittle"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/placementRulesBig")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Правила размещения животного c ограниченными возможностям").callbackData(String.format("/specialPlacementRules")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("Дайте собаке время на адаптацию в новом доме. " +
                    "Если желаете собаке добра, по приезду домой " +
                    "оставьте ее в покое, предоставив свободный " +
                    "доступ к воде и корму. Не навязывайте ей свое " +
                    "общество. Собаке потребуется от нескольких часов " +
                    "до нескольких дней, чтобы прийти в себя и оценить " +
                    "обстановку", data.contains("/placementRulesBig"))).replyMarkup(keyboardMarkup));
        }

        if (data.contains("/specialPlacementRules")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Первые советы кинолога").callbackData(String.format("/firstTipsDogHandler")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Питомца нужно правильно их кормить \n\n " +
                    "2. Питомцам инвалидам часто требуются специальная " +
                    "гимнастика и массажи, принудительное опорожнение " +
                    "мочевого пузыря и кишечника, профилактика пролежней " +
                    "и другие процедуры. \n\n " +
                    "3. Воспитание, дрессировка и регулярные игры необходимы " +
                    "для питомцев инвалидов точно так же, как и для обычных \n\n " +
                    "4. Если у вас дома уже есть кошка или собака, а вы решили " +
                    "взять животное с особенностями, соблюдайте общие правила " +
                    "их «знакомства» и делайте это постепенно ", data.contains("/specialPlacementRules"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/firstTipsDogHandler")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Причины обращения к кинологу").callbackData(String.format("/reasonsContactingDogHandler")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Не жалейте награды.Наградой для собаки может быть вкусное лакомство, " +
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
                            "если она подошла к вам восстановить эмоциональную связь. ",
                    data.contains("/firstTipsDogHandler"))).replyMarkup(keyboardMarkup));
        }

        if (data.contains("/reasonsContactingDogHandler")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа").callbackData(String.format("/reasonRefused")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Вы впервые решили завести щенка. Консультация кинолога еще до того, как в " +
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
                            "но не умеющие себя вести животные.",
                    data.contains("/reasonsContactingDogHandler"))).replyMarkup(keyboardMarkup));

        }
        if (data.contains("/reasonRefused")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Регистрация").callbackData(String.format("/registration")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("1. Отказ обеспечить безопасность питомца на новом месте \n\n " +
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
                    "11. Без объяснения причин \n\n ", data.contains("/reasonRefused"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/registration")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/callVolunteer")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Для повторного просмотра меню, нажмите кнопку ниже").callbackData(String.format("/Menu")));
        }
        if (data.contains("/Контактная_информация")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Приют для %s", data.startsWith("/Cats") ? "кошек работает: ..., адрес: ..., схема проезда: ..." : "собак работает: ..., адрес: ..., схема проезда: ...")));
        }
        if (data.contains("/REGISTRATIONOFCARPASS")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Контактные данные охраны %s", data.startsWith("/Cats") ? "кошачьего приюта, для оформления пропуска на машину: ..." : "собачьего приюта, для оформления пропуска на машину: ...")));
        }
        if (data.contains("/Техника_безопасности")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Общие рекомендации о технике безопасности на территории %s", data.startsWith("/Cats") ? "кошачьего приюта: ..." : "собачьего приюта: ...")));
        }
        if (data.contains("/LEAVECONTACTDETAILS")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Приют для %s", data.startsWith("/Cats") ? "кошек. Оставьте контактные данные для связи:" : "собак. Оставьте контактные данные для связи:")));
        }
        if (data.contains("/Позвать_волонтера")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Вызываю волонтера из приюта для %s", data.startsWith("/Cats") ? "кошек. Подождите..." : "собак. Подождите...")));
        }
    }

    public void sendMessage(Long chatId, String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

    private DogUser findOrSaveDogUser(User telegramUser) {
        DogUser persistentDogUser = dogUserRepositiry.findDogUsersById(telegramUser.id());
        if (persistentDogUser == null) {
            DogUser transientDogUser = DogUser.builder()
                    .telegramUserId(telegramUser.id())
                    .userName(telegramUser.username())
                    .firstNameDogUser(telegramUser.firstName())
                    .lastNameDogUser(telegramUser.lastName())
                    .state(DogStatus.LOGIN)
                    .build();
            return dogUserRepositiry.save(transientDogUser);

        }
        return persistentDogUser;
    }

    private CatUser findOrSaveCatUser(User telegramUser) {
        CatUser persistentCatUser = catUserRepository.findCatUsersById(telegramUser.id());
        if (persistentCatUser == null) {
            CatUser transientCatUser = CatUser.builder()
                    .telegramUserId(telegramUser.id())
                    .userName(telegramUser.username())
                    .firstNameCatUser(telegramUser.firstName())
                    .lastNameCatUser(telegramUser.lastName())
                    .state(CatStatus.LOGIN)
                    .build();
            return catUserRepository.save(transientCatUser);


        }
        return persistentCatUser;
    }


}



