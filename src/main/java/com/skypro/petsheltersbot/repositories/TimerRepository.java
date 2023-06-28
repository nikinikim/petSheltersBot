package com.skypro.petsheltersbot.repositories;

import com.skypro.petsheltersbot.entity.InfoMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimerRepository extends JpaRepository<InfoMessage, Long> {
    List<InfoMessage> timer(LocalDateTime localDateTime);
}
