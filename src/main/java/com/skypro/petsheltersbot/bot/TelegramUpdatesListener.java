package com.skypro.petsheltersbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.skypro.petsheltersbot.config.CatStatus;
import com.skypro.petsheltersbot.config.DogStatus;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
//import com.skypro.petsheltersbot.service.CatUserService;
import com.skypro.petsheltersbot.service.DogUserService;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TelegramUpdatesListener implements UpdatesListener {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramUpdatesListener.class);

    private final List<TelegramHandler> handlers;

    private final TelegramBot telegramBot;

      private final Map<Long, DogStatus> dogStatus = new HashMap<>();

    private final Map<Long, CatStatus> catStatus = new HashMap<>();


    @Autowired
    public TelegramUpdatesListener(List<TelegramHandler> handlers, TelegramBot telegramBot, DogUserService dogUserService) {
        this.handlers = handlers;
        this.telegramBot = telegramBot;
               this.telegramBot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        updates.stream()
                .filter(update -> update.message() != null || update.callbackQuery() != null)
                .forEach(this::handleUpdate);
        return CONFIRMED_UPDATES_ALL;
    }

   @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    private void handleUpdate(Update update) {
        TelegramHandler matchetHandler = null;
        int maxWeight = Integer.MIN_VALUE;
        for (TelegramHandler handler : handlers) {
            int weight = handler.getWeight(update);
            if (maxWeight < weight) {
                maxWeight = weight;
                matchetHandler = handler;
            }
        }
        assert matchetHandler != null;
        matchetHandler.handleUpdate(update);
    }
}
