package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skypro.petsheltersbot.entity.InfoMessage;
import com.skypro.petsheltersbot.repositories.InfoMessageRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private final InfoMessageRepository infoMessageRepository;
    private final List<TelegramHandler> handlers;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
               updates.stream().filter(update -> update.message() != null || update.callbackQuery() != null).forEach(this::handlerUpdate);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void handlerUpdate(Update update) {
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/Информация_о_приюте")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Информация о приюте"));
            }
            if (data.equals("/CONTACTINFO")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Расписание работы приюта, адрес, схема проезда"));
            }
            if (data.equals("/REGISTRATIONOFCARPASS")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Контактные данные охраны для оформления пропуска на машину"));
            }
            if (data.equals("/SAFETYSHELTER")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Общие рекомендации о технике безопасности на территории приюта"));
            }
            if (data.equals("/LEAVECONTACTDETAILS")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Оставить контактные данные для связи"));
            }
            if (data.equals("/Позвать_волонтера")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вызываю волонтера. Подождите..."));
            }
        } else {
            for (TelegramHandler handler : handlers) {
                if (handler.appliesTo(update)) {
                    handler.handlerUpdate(update);
                    break;
                }
            }
        }
    }

        public void sendMessage(Long chatId, String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }
}
