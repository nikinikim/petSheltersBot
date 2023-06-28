package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.petsheltersbot.entity.Photo;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
import com.skypro.petsheltersbot.repositories.InfoMessageRepository;
import com.skypro.petsheltersbot.repositories.ReportCatsRepository;
import com.skypro.petsheltersbot.repositories.ReportDogsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.time.LocalDateTime.parse;
import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.update;

@Service
@AllArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Autowired
    private TelegramBot telegramBot;

    private final InfoMessageRepository infoMessageRepository;
    private final ReportDogsRepository reportDogsRepository;
    private final ReportCatsRepository reportCatsRepository;
    private final List<TelegramHandler> handlers;

    @Autowired
    public TelegramBotUpdatesListener(List<TelegramHandler> handlers, TelegramBot telegramBot, InfoMessageRepository infoMessageRepository, ReportDogsRepository reportDogsRepository, ReportCatsRepository reportCatsRepository) {
        this.handlers = handlers;
        this.telegramBot = telegramBot;
        this.infoMessageRepository = infoMessageRepository;
        this.reportDogsRepository = reportDogsRepository;
        this.reportCatsRepository = reportCatsRepository;
        this.telegramBot.setUpdatesListener(this);
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.stream().filter(update -> update.message() != null || update.callbackQuery() != null).forEach(this::handlerUpdate);
//        try {
//            updates.stream()
//                    .filter(update -> update.message().photo()!= null)
//                    .forEach(
//                    update -> {
//                        PhotoSize[] photoSizes = update.message().photo();
//                        PhotoSize photoSize = photoSizes[photoSizes.length - 1];
//                        GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
//                        if (getFileResponse.isOk()) {
//                            try {
//                                String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
//                                String mediaType = switch (extension) {
//                                    case "png" -> MediaType.IMAGE_PNG_VALUE;
//                                    case "jpg", "jpeg" -> MediaType.IMAGE_JPEG_VALUE;
//                                    case "gif" -> MediaType.IMAGE_GIF_VALUE;
//                                    default -> "image/*";
//                                };
//                                byte[] data = telegramBot.getFileContent(getFileResponse.file());
//                                Photo photo = new Photo(mediaType, data);
////                    Files.write(Paths.get(UUID.randomUUID() + "." + extension), data1);
//
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//
//                        }
//                    });
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    //}
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


    private void handlerUpdatePet(String data, Long fromId) {
        if (data.contains("/Информация_о_приюте")) {
            this.telegramBot.execute(new SendMessage(fromId, String.format("Приют для %s", data.startsWith("/Cats") ? "кошек. Основан..." : "собак. Основан...")));
        }
        if (data.contains("/report")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчет об общем самочувстие питомца").callbackData(String.format("/Состояние_питомца")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить рацион").callbackData(String.format("/Рацион")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Отправить фото").callbackData(String.format("/photo")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/Волонтер")));
            telegramBot.execute(new SendMessage(fromId, String.format("""
                    Какой отчет вам нужно отправить? Если вы отправляете отчет первый раз, то нажимайте первую кнопку, а потом далее.
                    Если вам нужно отправить что-то одно, то выберите нужную кнопку.
                    Если нужно позвать волнотера, то нажмите соотвествующую кнопку. 
                    """, fromId)).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/Состояние_питомца")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData(String.format("/Рацион")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/Волонтер")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData(String.format("/report")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("""
                            Пожалуйста, опишите как чувствует себя ваш питомец на новом месте:
                            1. Проявляет ли он агрессию или слишком аппатичный?
                            2. Появились ли какие-то болезни?
                            3. Часто ли линяет?
                            4. Как ведет себя с другими питомцами?
                            5. Какие привычки у него повились?
                            6. Хорошо ли прошла адаптация к новому дому?                
                                            
                            """,
                    data.contains("/Состояние_питомца"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/Рацион")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData(String.format("/photo")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/Волонтер")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData(String.format("/report")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("""
                            Пожалуйста, опишите рацион вашего питомца: 
                            натуральная еда или специальный сухой/жидкий корм(
                            название корма, который вы используете) и сколько грамм в день.
                            """,
                    data.contains("/Рацион"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/photo")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Меню").callbackData(String.format("/report")));
            keyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData(String.format("/Волонтер")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("Для приклепления фото нажмите на скрепку внизу и далее отправить.",
                    data.contains("/photo"))).replyMarkup(keyboardMarkup));
        }
        if (data.contains("/Волонтер")) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
            keyboardMarkup.addRow(new InlineKeyboardButton("Далее").callbackData(String.format("/next")));
            this.telegramBot.execute(new SendMessage(fromId, String.format("Позвать волонтера",
                    data.contains("/Волонтер"))).replyMarkup(keyboardMarkup));
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

    /**
     * @param chatId
     * @param message Метод по отправке сообщения
     */
    public void sendMessage(Long chatId, String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

    @Nullable
    private LocalDateTime parse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }


    private void sendResponse(SendMessage sendMessage) {
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.message());
        }
    }

}

