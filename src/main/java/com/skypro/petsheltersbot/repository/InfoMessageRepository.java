package com.skypro.petsheltersbot.repository;

import com.skypro.petsheltersbot.entity.InfoMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface InfoMessageRepository extends JpaRepository<InfoMessage, Long> {
    @Query("select n from InfoMessage n where n.sendDate between ?1 and ?2")
    List<InfoMessage> findNInfoMessageBySendDateBetween(LocalDateTime d1, LocalDateTime d2);
}