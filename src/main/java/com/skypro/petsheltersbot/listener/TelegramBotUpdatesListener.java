package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
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

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.stream().filter(update -> update.message() != null || update.callbackQuery() != null).forEach(this::handlerUpdate);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Обработка ответа при выборе приюта
     *
     * @param update
     */
    private void handlerUpdate(Update update) {
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
    }

    /**
     * Обработка ответа при выборе направлений
     *
     * @param data
     * @param fromId
     */
    private void handlerUpdatePet(String data, Long fromId) {
        if (data.contains("/Информация_о_приюте")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Приют для %s", data.startsWith("/Cats") ? "кошек. Основан..." : "собак. Основан...")));
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
}
