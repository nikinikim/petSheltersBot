package com.skypro.petsheltersbot.schedule;

import com.skypro.petsheltersbot.repositories.InfoMessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import com.skypro.petsheltersbot.entity.InfoMessage;
import com.skypro.petsheltersbot.listener.TelegramBotUpdatesListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class NotificationSchedule {
    private Logger LOG = LoggerFactory.getLogger(NotificationSchedule.class);

    private final TelegramBotUpdatesListener telegramBotUpdatesListener;

    private final InfoMessageRepository infoMessageRepository;

    @Scheduled(cron = "${data.schedule.notification-schedule.cron}")
    public void execute() {
        LOG.info("Processing scheduling");
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<InfoMessage> list = infoMessageRepository.findNInfoMessageBySendDateBetween(now, now.plusSeconds(59));
        list.forEach(i -> telegramBotUpdatesListener.sendMessage(i.getChatId(), i.getNotification()));

    }
}
