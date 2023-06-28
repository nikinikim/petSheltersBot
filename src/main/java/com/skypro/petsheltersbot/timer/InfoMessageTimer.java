package com.skypro.petsheltersbot.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.repositories.TimerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
@Component
public class InfoMessageTimer {
    private final TimerRepository timerRepository;
    private final TelegramBot telegramBot;

    public InfoMessageTimer(TimerRepository timerRepository, TelegramBot telegramBot) {
        this.timerRepository = timerRepository;
        this.telegramBot = telegramBot;
    }


    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
    public void task() {
        timerRepository.timer(
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
        ).forEach(infoMessage -> {
            telegramBot.execute(new SendMessage(infoMessage.getChatId(), infoMessage.getNotification()));
            timerRepository.delete(infoMessage);
        });

    }
}
