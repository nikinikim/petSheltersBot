package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.CatStatus;
import com.skypro.petsheltersbot.service.CatUserService;

import java.util.HashMap;
import java.util.Map;

public class GetCatHandler extends AbstractMessagingHandler {

    private final CatUserService catService;

        public GetCatHandler(TelegramBot telegramBot, CatUserService catService) {
        super(telegramBot);
        this.catService = catService;
    }

    private final Map<Long, CatStatus> catStatus = new HashMap<>();

    @Override


    @Override
    public void handleUpdate(Update update) {
        Long chatID = update.message().chat().id();
        String text = update.message().text();
        CatStatus status = catStatus.get(update.message().chat().id());
        if (status != null) {
            switch (status) {
                case DATING_RULES -> {
                    catService.addruleDating(chatID, text);
                    catStatus.put(chatID, CatStatus.DOCUMENT_LIST);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/ruleDating"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "1. Подробно расспросите сотрудников приюта о кошке/котёнке \n\n " +
                                    "2. Понаблюдайте за поведением кошки/котёнка в вольере \n\n " +
                                    "3. Сфотографируйте кошку/котёнка или сделайте видео и покажите членам семьи \n\n " +
                                    "4. Покажите кошку/котёнка ветеринару или знакомому с опытом содержания кошек \n\n " +
                                    "5. Познакомьтесь с кошкой/котёнком, пока она находится в приюте \n\n " +
                                    "6. Проведите первичный осмотр кошки/котёнка в приюте \n\n " +
                                    "7. Подержите на руках или побудьте рядом с кошкой/котёнком в присутствии волонтёра \n\n " +
                                    "8. Постарайтесь привезти кошку/котёнка к себе домой в гости \n\n " +
                                    " Для ознакомления со списком документов по усыновлению кошки/котёнка, нажмите /documents"));

                }
                case DOCUMENT_LIST -> {
                    catService.adddocuments(chatID, text);
                    catStatus.put(chatID, CatStatus.TRANSPORTIRATION_RULES);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/documents"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "1. Паспорт \n\n " +
                                    "2. Выписка из трудовой книжки \n\n " +
                                    "3. Выписку из домовой книги \n\n " +
                                    "4. Выписку с банковского счета \n\n " +
                                    " Для ознакомления с правилами транспортировки питомца, нажмите /transportirationRules"));

                }
                case TRANSPORTIRATION_RULES -> {
                    catService.addTransportirationRules(chatID, text);
                    catStatus.put(chatID, CatStatus.PLACEMENT_RULES_LITTLE);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/transportirationRules"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "Перевозить собаку из приюта домой лучше в специальном контейнере-переноске. \n\n " +
                                    "Некоторые кошки испытывают стресс в автомобиле, особенно если раньше они \n\n " +
                                    "на автомобиле не катались. Наличие специальной перевозки сделает поездку \n\n " +
                                    "легче и для Вас, и для кошки. Если не хотите покупать себе собственную \n\n " +
                                    "перевозку, можете попросить у человека, у которого берете кошку, или у друзей. \n\n" +
                                    "Запаситесь дополнительными покрывалами на сидения,  бумажными полотенцами, \n\n " +
                                    "спросите в приюте или у волонтеров об особенностях перевозки кошки в машине. \n\n " +
                                    "Для ознакомления с правилами размещения котёнка " +
                                    "после приезда из приюта, нажмите /placementRulesLittle"));

                }
                case PLACEMENT_RULES_LITTLE -> {
                    catService.addPlacementRulesLittle(chatID, text);
                    catStatus.put(chatID, CatStatus.PLACEMENT_RULES_BIG);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/placementRulesLittle"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "1. Перед тем как взять кошку/котёнка из приюта," +
                                    "обязательно выясните, все ли прививки сделали кошке/котёнку \n\n " +
                                    "2. Не организовывайте для кошки/котёнка лишь одно место, " +
                                    "где ей позволено спать и лежать. \n\n " +
                                    "3. Оборудуйте один из укромных уголков для кошки/котёнка у вашей кровати \n\n " +
                                    "4. Старайтесь побольше общаться с питомцем \n\n " +
                                    "5. Дайте щенку побольше игрушек \n\n " +
                                    " Для ознакомления с правилами размещения взрослой кошки " +
                                    " после приезда из приюта, нажмите /placementRulesBig"));
                }
                case PLACEMENT_RULES_BIG -> {
                    catService.addPlacementRulesBig(chatID, text);
                    catStatus.put(chatID, CatStatus.SPECIAL_PLACEMENT_RULES);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/placementRulesBig"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "1. Для ознакомления с правилами размещения кошки/котёнка с" +
                                    " ограниченными возможностями после приезда из приюта," +
                                    " нажмите /specialPlacementRules"));
                }
                case SPECIAL_PLACEMENT_RULES -> {
                    catService.addSpecialPlacementRules(chatID, text);
                    catStatus.put(chatID, CatStatus.REASON_REFUSED);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/specialPlacementRules"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "1. Для получения информации о причинах отказа отдавать кошку/котёнка, " +
                                    " нажмите /firstTipsDogHandler"));
                }
                case REASON_REFUSED -> {
                    catService.addReasonRefused(chatID, text);
                    catStatus.put(chatID, CatStatus.REGISTRATION);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/reasonRefused"));
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
                                    "11. Без объяснения причин \n\n " +
                                    "Если Вы хотите зарегистрироваться на нашем сайте, нажмите /registration"));
                }
                case REGISTRATION -> {
                    catStatus.put(chatID, CatStatus.CALL_VOLUNTEER);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/registration"));
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "Все интересующие вопросы Вы можете задать нашим волонтерам, нажав /callVolunteer"));
                }
            }
        } else if (update.message().text() != null && update.message().text().equals("/GetCat")) {
            catStatus.put(update.message().chat().id(), CatStatus.DATING_RULES);
            telegramBot.execute(
                    new SendMessage(update.message().chat().id(),
                            "Привет " + update.message().from().firstName()));
            telegramBot.execute(new SendMessage(update.message().chat().id(), "Для ознакомления с правилами первого знакомства с питомцем, нажмите /ruleDating"));
        }
    }
}
