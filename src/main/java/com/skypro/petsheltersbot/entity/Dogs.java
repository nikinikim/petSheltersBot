package com.skypro.petsheltersbot.entity;

import com.pengrad.telegrambot.model.PhotoSize;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "photo", nullable = false)
    private PhotoSize photoSize;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getNickname() {
                return nickname;
        }

        public void setNickname(String nickname) {
                this.nickname = nickname;
        }

        public String getBreed() {
                return breed;
        }

        public void setBreed(String breed) {
                this.breed = breed;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
