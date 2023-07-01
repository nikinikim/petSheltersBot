package com.skypro.petsheltersbot.repository;

import com.skypro.petsheltersbot.entity.InfoMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimerRepository extends JpaRepository<InfoMessage, Long> {

    List<InfoMessage> findAllByNotificationDateTime(LocalDateTime localDateTime);


}

