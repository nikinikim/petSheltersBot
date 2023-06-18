package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.UserStatus;
import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.repository.DogUserRepositiry;
import com.skypro.petsheltersbot.service.DogUserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationHandler extends AbstractMessagingHandler {

    private final DogUserService dogUserService;

    private final DogUserRepositiry dogUserRepositiry;

 //   private final Map<Long, UserStatus> userStatuses = new HashMap<>();

    public RegistrationHandler(TelegramBot telegramBot, DogUserService dogUserService, DogUserRepositiry dogUserRepositiry) {
        super(telegramBot);
        this.dogUserService = dogUserService;
        this.dogUserRepositiry = dogUserRepositiry;
    }


    @Override
    public int getWeight(Update update) {
        int weight = 0;
        if (update.message().text() != null) {
            weight += 1;
        }
        if (update.message().text() != null || update.message().text().equals("/registration")) {
            weight += 1;
            telegramBot.execute(
                    new SendMessage(update.message().chat().id(),
                            "Привет " + update.message().from().firstName()));
        } else if (update.message().text().equals("/Enter")) {
            weight += 1;
        }
        return weight;
    }

    @Override
    public void handleUpdate(Update update) {
        Long chatID = update.message().chat().id();
        Message text = update.message();
        User telegramUser = text.from();
        DogUser dogUser = findOrSaveDogUser(telegramUser);

//        UserStatus status = userStatuses.get(update.message().chat().id());
//        if (status != null) {
//            switch (status) {
//                case LOGIN -> {
//                    dogUserRegistrationService.addDogLogin(chatID, text);
//                    userStatuses.put(chatID, UserStatus.PASSWORD);
//                    telegramBot.execute(new SendMessage(update.message().chat().id(), "Введите пароль"));
//                    Message textMessage = update.message();
//                    User telegramUser = textMessage.from();
//                    AppUser dogUser = findOrSaveDogUser(telegramUser);
//
//                }
//                case PASSWORD -> {
//                    dogUserRegistrationService.addDogPassword(chatID, text);
//                    userStatuses.put(chatID, UserStatus.DATA_ENTERED);
//                    telegramBot.execute(new SendMessage(update.message().chat().id(), "Ввод успешен"));
//
//
//                }
//                case DATA_ENTERED -> {
//                    telegramBot.execute(new SendMessage(chatID, (chatID).toString()));
//                    telegramBot.execute(
//                            new SendMessage(update.message().chat().id(),
//                                    "Привет " + update.message().from().firstName()));
//                }
//            }
//        } else if (update.message().text() != null && update.message().text().equals("/registration")) {
//            userStatuses.put(update.message().chat().id(), UserStatus.LOGIN);
//            telegramBot.execute(new SendMessage(update.message().chat().id(), "Введите логин"));
//        }
//
//    }
//
//    public AppUser findOrSaveDogUser(User telegramUser) {
//        AppUser persistentDogUser = dogUserRepositiry.findDogUsersById(telegramUser.id());
//        if (persistentDogUser == null) {
//            AppUser transientDogUser = AppUser.builder()
//                    .telegramUserID(String.valueOf(telegramUser.id()))
//                    .firstNameDogUser(telegramUser.firstName())
//                    .lastNameDogUser(telegramUser.lastName())
//                    .userStatus(UserStatus.LOGIN)
//                    .build();
//            return dogUserRepositiry.save(transientDogUser);
//        }
//        return persistentDogUser;
//    }


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
