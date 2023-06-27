package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.UserStatus;
import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.repository.DogUserRepositiry;
import com.skypro.petsheltersbot.service.DogUserService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.awt.SystemColor.text;

@Component
@Order(9)
public class RegistrationHandler extends AbstractMessagingHandler {

    private final DogUserService dogUserService;

    private final DogUserRepositiry dogUserRepositiry;

    private final Map<Long, UserStatus> userStatuses = new HashMap<>();

    public RegistrationHandler(TelegramBot telegramBot, DogUserService dogUserService, DogUserRepositiry dogUserRepositiry) {
        super(telegramBot);
        this.dogUserService = dogUserService;
        this.dogUserRepositiry = dogUserRepositiry;
    }
    @Override
    public void handleUpdate(Update update) {
        Long chatID = update.message().chat().id();
        User telegramUser = update.message().from();
        String text = update.message().text();
        DogUser dogUser = findOrSaveDogUser(telegramUser);
        UserStatus status = userStatuses.get(update.message().chat().id());
        if (status != null) {
            switch (status) {
                case LOGIN -> {
                    dogUserService.addLogin(chatID, text);
                    userStatuses.put(chatID, UserStatus.PASSWORD);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "Введите пароль"));

                }
                case PASSWORD -> {
                    dogUserService.addPassword(chatID, text);
                    userStatuses.put(chatID, UserStatus.DATA_ENTERED);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "Ввод успешен"));
                    dogUserService.getUser(chatID);
                    telegramBot.execute(new SendMessage(update.message().chat().id(), "/enter"));
                    telegramBot.execute(
                            new SendMessage(update.message().chat().id(),
                                    "Привет " + update.message().from().firstName()));


                }
                case DATA_ENTERED -> {
                    telegramBot.execute(new SendMessage(chatID, dogUserService.getUser(chatID).toString()));
                    telegramBot.execute(
                            new SendMessage(update.message().chat().id(),
                                    "Привет " + update.message().from().firstName()));
                }
            }
        } else if (update.message().text() != null && update.message().text().equals("/registration")) {
            userStatuses.put(update.message().chat().id(), UserStatus.LOGIN);
            telegramBot.execute(new SendMessage(update.message().chat().id(), "Введите логин"));
        }


    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/registrationDog");
    }

    private DogUser findOrSaveDogUser(User telegramUser){
        DogUser persistentDogUser = dogUserRepositiry.findDogUsersById(telegramUser.id());
        if (persistentDogUser == null){
            DogUser transientDogUser = DogUser.builder()
                    .telegramUserId(telegramUser.id())
                    .userName(telegramUser.username())
                    .firstNameDogUser(telegramUser.firstName())
                    .lastNameDogUser(telegramUser.lastName())
                    .state(UserStatus.LOGIN)
                    .build();
            return dogUserRepositiry.save(transientDogUser);

        }
        return persistentDogUser;
    }

}
