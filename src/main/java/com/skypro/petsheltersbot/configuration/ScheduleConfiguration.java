//package com.skypro.petsheltersbot.configuration;
//
//import com.skypro.petsheltersbot.listener.TelegramBotUpdatesListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import com.skypro.petsheltersbot.repository.InfoMessageRepository;
//import com.skypro.petsheltersbot.schedule.NotificationSchedule;
//
//@Configuration
//@EnableScheduling
//public class ScheduleConfiguration {
//    @Bean
//    public NotificationSchedule notificationSchedule(@Autowired TelegramBotUpdatesListener telegramBotUpdatesListener, @Autowired InfoMessageRepository infoMessageRepository) {
//        return new NotificationSchedule(telegramBotUpdatesListener, infoMessageRepository);
//    }
//}
