package com.skypro.petsheltersbot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "message", schema = "public", catalog = "telegram")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "notification")
    private String notification;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

}

