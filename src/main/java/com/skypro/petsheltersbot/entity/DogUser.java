package com.skypro.petsheltersbot.entity;


import com.skypro.petsheltersbot.configuration.DogStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column
    private Long telegramUserId;
    @Column
    public String login;
    @Column
    public String password;
    @Column
    private String userName;
    @Column
    private DogStatus state;

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
