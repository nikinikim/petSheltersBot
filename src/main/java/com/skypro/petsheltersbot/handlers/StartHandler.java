package com.skypro.petsheltersbot.handlers;

import antlr.StringUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import liquibase.util.StringUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class StartHandler extends AbstractMessagingHandler {

    public StartHandler(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/start");
    }

    @Override
    public void handlerUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        /*if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/Cats") || data.equals("/Dogs")) {
                this.handlerUpdateAnimal(update, keyboardMarkup);
            }
        } else {*/
        keyboardMarkup.addRow(new InlineKeyboardButton("Приют для кошек").callbackData("/Cats"),
                new InlineKeyboardButton("Приют для собак").callbackData("/Dogs"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Привет, %s! Выберите приют, нажав на кнопку ниже:", update.message().from().firstName())).replyMarkup(keyboardMarkup));
        //}
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {
        if (!StringUtil.isEmpty(petType)) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData(String.format("%s/Информация_о_приюте", petType)));
            keyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес, схема проезда").callbackData(String.format("%s/Контактная_информация", petType)));
            keyboardMarkup.addRow(new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData(String.format("%s/REGISTRATIONOFCARPASS", petType)));
            keyboardMarkup.addRow(new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData(String.format("%s/Техника_безопасности", petType)));
            keyboardMarkup.addRow(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData(String.format("%s/LEAVECONTACTDETAILS", petType)));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("%s/Позвать_волонтера", petType)));
            telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), String.format("Выберите направление, нажав на кнопку ниже:"/*, update.message().from().firstName()*/)).replyMarkup(keyboardMarkup));
        }
//        else {
//            telegramBot.execute(new SendMessage(update.message().chat().id(), "Для продолжения выберите направление для ознакомления или нажмите кнопку 'позвать волонтёра'"));
//        }
    }

    /*@Override
    public void handlerUpdateCat(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/Информация_о_приюте"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес, схема проезда").callbackData("/CONTACTINFO"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/REGISTRATIONOFCARPASS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData("/SAFETYSHELTER"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("/LEAVECONTACTDETAILS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData("/Позвать_волонтера"));
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), String.format("%s, выберите направление", "update.message().from().firstName()")).replyMarkup(keyboardMarkup));
    }

    @Override
    public void handlerUpdateDog(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте").callbackData("/Информация_о_приюте"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес, схема проезда").callbackData("/CONTACTINFO"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/REGISTRATIONOFCARPASS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData("/SAFETYSHELTER"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("/LEAVECONTACTDETAILS"));
        keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData("/Позвать_волонтера"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("%s, выберите направление", update.message().from().firstName())).replyMarkup(keyboardMarkup));
    }*/
}
