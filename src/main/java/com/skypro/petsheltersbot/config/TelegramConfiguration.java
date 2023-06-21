package com.skypro.petsheltersbot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.skypro.petsheltersbot.listener.TelegramBotUpdatesListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.beans.factory.annotation.Value;

@Configuration
public class TelegramConfiguration {
    @Bean
    public TelegramBot telegramBot(@Value("${telegram.bot.token}")String token){
        return new TelegramBot(token);

    }

}
