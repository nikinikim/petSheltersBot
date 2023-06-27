package com.skypro.petsheltersbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.config.UserStatus;
import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.repository.CatUserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(9)
public class RegistrationCatHandler extends AbstractMessagingHandler{
    public RegistrationCatHandler(TelegramBot telegramBot, CatUserRepository catUserRepository) {
        super(telegramBot);
        this.catUserRepository = catUserRepository;
    }
    private final CatUserRepository catUserRepository;

     @Override
    public void handleUpdate(Update update) {
        Long chatID = update.message().chat().id();
        Message text = update.message();
        User telegramUser = text.from();
        CatUser catUser = findOrSaveCatUser(telegramUser);
    }

    @Override
    public void handlerUpdatePet(Update update, String petType) {

    }

    @Override
    public boolean appliesTo(Update update) {
        return update.message().text() != null && update.message().text().equals("/registration");
    }
    private CatUser findOrSaveCatUser(User telegramUser){
        CatUser persistentCatUser = catUserRepository.findCatUsersById(telegramUser.id());
        if (persistentCatUser == null){
            CatUser transientCatUser = CatUser.builder()
                    .telegramUserId(telegramUser.id())
                    .userName(telegramUser.username())
                    .firstNameCatUser(telegramUser.firstName())
                    .lastNameCatUser(telegramUser.lastName())
                    .state(UserStatus.LOGIN)
                    .build();
            return catUserRepository.save(transientCatUser);

        }
        return persistentCatUser;
    }

   }
