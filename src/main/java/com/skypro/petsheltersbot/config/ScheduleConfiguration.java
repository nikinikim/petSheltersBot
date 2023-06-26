//package com.skypro.petsheltersbot.config;
//
//import com.skypro.petsheltersbot.bot.TelegramBotUpdatesListener;
//import com.skypro.petsheltersbot.repository.InfoMessageRepository;
//import com.skypro.petsheltersbot.schedule.NotificationSchedule;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@Configuration
//@EnableScheduling
//public class ScheduleConfiguration {
//    @Bean
//    public NotificationSchedule notificationSchedule(@Autowired TelegramBotUpdatesListener telegramBotUpdatesListener, @Autowired InfoMessageRepository infoMessageRepository) {
//        return new NotificationSchedule(telegramBotUpdatesListener, infoMessageRepository);
//    }
//}
