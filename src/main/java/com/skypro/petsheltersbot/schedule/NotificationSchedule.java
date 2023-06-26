//package com.skypro.petsheltersbot.schedule;
//
//import com.skypro.petsheltersbot.bot.TelegramBotUpdatesListener;
//import com.skypro.petsheltersbot.entity.InfoMessage;
//import com.skypro.petsheltersbot.repository.InfoMessageRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class NotificationSchedule {
//    private Logger LOG = LoggerFactory.getLogger(NotificationSchedule.class);
//
//    private final TelegramBotUpdatesListener telegramBotUpdatesListener;
//
//    private final InfoMessageRepository infoMessageRepository;
//
//    @Scheduled(cron = "${data.schedule.notification-schedule.cron}")
//    public void execute() {
//        LOG.info("Processing scheduling");
//        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
//        List<InfoMessage> list = infoMessageRepository.findNInfoMessageBySendDateBetween(now, now.plusSeconds(59));
//        list.forEach(i -> telegramBotUpdatesListener.sendMessage(i.getChatId(), i.getNotification()));
//    }
//}