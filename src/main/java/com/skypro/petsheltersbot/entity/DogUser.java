package com.skypro.petsheltersbot.entity;

import com.skypro.petsheltersbot.config.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_shelter")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstNameDogUser;

    @Column
    private String lastNameDogUser;

    private Long telegramUserId;

    public String login;

    public String password;

    private String userName;

    private UserStatus state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstNameDogUser() {
        return firstNameDogUser;
    }

    public void setFirstNameDogUser(String firstNameDogUser) {
        this.firstNameDogUser = firstNameDogUser;
    }

    public String getLastNameDogUser() {
        return lastNameDogUser;
    }

    public void setLastNameDogUser(String lastNameDogUser) {
        this.lastNameDogUser = lastNameDogUser;
    }
}
