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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private final InfoMessageRepository infoMessageRepository;
    private final List<TelegramHandler> handlers;

//    public TelegramBotUpdatesListener(TelegramBot telegramBot, InfoMessageRepository infoMessageRepository, List<TelegramHandler> handlers) {
//        this.telegramBot = telegramBot;
//        this.infoMessageRepository = infoMessageRepository;
//        this.handlers = handlers;
//        this.telegramBot.setUpdatesListener(this);
//    }

//
//    private boolean start;
//
//    @PostConstruct
//    public void init() {
//        telegramBot.setUpdatesListener(this);
//    }

    @Override
    public int process(List<Update> updates) {
        updates.stream().filter(update -> update.message() != null || update.callbackQuery() != null).forEach(this::handlerUpdate);
//
//        updates.forEach(update -> {
//            long chatId = update.message().chat().id();
//            String text = update.message().text();
//            if (text.equals("/start")) {
//                this.start = true;
//                sendMessage(chatId, "Привет!"+update.message().from().firstName());
//            } else if (text.equals("/stop")) {
//                this.start = false;
//                sendMessage(chatId, "By!");
//            } else {
//                if (this.start) {
//                    Matcher matcher = Pattern.compile("[0-9\\.\\:\\s]{16}").matcher(update.message().text());
//                    matcher.find();
//                    String notificationDate = matcher.group().trim();
//                    matcher = Pattern.compile("(\\s)([\\W+]+)").matcher(update.message().text());
//                    matcher.find();
//                    String notificationMessage = matcher.group().trim();
//                    saveMessage(chatId, notificationDate, notificationMessage);
//                } else {
//                    sendMessage(chatId, "Для старта наберите: /start");
//                }
//            }
//            logger.info("Processing update: {}", update);
//            // Process your updates here
//        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void handlerUpdate(Update update) {
//        if (update.message().text() != null) {
//            processText(update);
//        } else {
//            sendMessage(update.message().chat().id(), "Для старта наберите: /start");
//        }
        if (update.callbackQuery() != null) {
            String data = update.callbackQuery().data();
            if (data.equals("/Информация о приюте")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Информация о приюте"));
            }
            if (data.equals("/Расписание работы приюта, адрес, схема проезда")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Расписание работы приюта, адрес, схема проезда"));
            }
            if (data.equals("/Контактные данные охраны для оформления пропуска на машину")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Контактные данные охраны для оформления пропуска на машину"));
            }
            if (data.equals("/Общие рекомендации о технике безопасности на территории приюта")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Общие рекомендации о технике безопасности на территории приюта"));
            }
            if (data.equals("/Оставить контактные данные для связи")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вывод: Оставить контактные данные для связи"));
            }
            if (data.equals("/Позвать волонтера")) {
                this.telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), "Вызываю волонтера. Подождите..."));
            } else {
                for (TelegramHandler handler : handlers) {
                    if (handler.appliesTo(update)) {
                        handler.handlerUpdate(update);
                        break;
                    }
                }
            }
        }
    }

//    private void processText(Update update) {
//        Long chatId = update.message().chat().id();
//        String text = update.message().text();
//        switch (text) {
//            case "/start" -> sendMessage(chatId, String.format("Привет, %s! Выберите следующие темы:", update.message().from().firstName()));
//            case "/Информация о приюте" -> sendMessage(chatId, String.format("Информация о приюте"));
//        }
//    }

//    @Transactional
//    public void saveMessage(Long chatId, String notificationDate, String notificationMessage) {
//        InfoMessage infoMessage = new InfoMessage();
//        infoMessage.setChatId(chatId);
////        infoMessage.setDate(notificationDate);
//        infoMessage.setNotification(notificationMessage);
//        if (!notificationDate.isEmpty())
//            infoMessage.setSendDate(LocalDateTime.parse(notificationDate, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
//        infoMessageRepository.save(infoMessage);
//    }

        public void sendMessage(Long chatId, String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

//    public void sendMessage(Long chatId, String message) {
//        SendMessage sendMessage = new SendMessage(chatId, message);
//        SendResponse sendResponse = telegramBot.execute(sendMessage);
//        if (!sendResponse.isOk()) {
//            LOG.error("Error during sending message: {}", sendResponse.description());
//        }
//    }
}
