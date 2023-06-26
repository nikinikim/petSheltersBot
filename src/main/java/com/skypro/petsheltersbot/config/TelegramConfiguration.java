package com.skypro.petsheltersbot.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.telegram.bot")
public class TelegramConfiguration {

    private String token;

    @Bean
    public TelegramBot bot() {
        return new TelegramBot(this.token);
    }
    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token=token;
    }


}
