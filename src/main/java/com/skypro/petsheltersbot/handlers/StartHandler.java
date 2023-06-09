package com.skypro.petsheltersbot.handlers;

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

    /**
     * Создание стартовой кнопки
     *
     * @param update {@link Update}
     * @return
     */
    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/start");
    }

    /**
     * Создание кнопок выбора приюта
     *
     * @param update
     */
    @Override
    public void handleUpdate(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.addRow(new InlineKeyboardButton("Приют для кошек").callbackData("/Cats"),
                new InlineKeyboardButton("Приют для собак").callbackData("/Dogs"));
        telegramBot.execute(new SendMessage(update.message().chat().id(), String.format("Привет, %s! Выберите приют, нажав на кнопку ниже:", update.message().from().firstName())).replyMarkup(keyboardMarkup));

    }

    /**
     * Создание кнопок направлений
     *
     * @param update
     * @param petType
     */
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
    }
}
