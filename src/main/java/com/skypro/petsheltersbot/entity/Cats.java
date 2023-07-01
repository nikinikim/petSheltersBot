package com.skypro.petsheltersbot.entity;

import javax.persistence.*;
    @Entity
    @Table(name = "cats")
    public class Cats {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long id;
        @Column(name = "nickname", nullable = false)
        private String nickname;
        @Column(name = "age", nullable = false)
        private int age;
        @Column(name = "message", nullable = false)
        private String message;
        @ManyToOne
        @JoinColumn(name = "photo_id")
        private Photo photo;

        public Cats(long id, String nickname, int age, String message, Photo photo) {
            this.id = id;
            this.nickname = nickname;
            this.age = age;
            this.message = message;
            this.photo = photo;
        }

        public Cats() {

        }

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

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }
    }

