package com.skypro.petsheltersbot.entity;

import com.skypro.petsheltersbot.config.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_users")
public class CatUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstNameCatUser;
    @Column
    private String lastNameCatUser;

    private Long telegramUserId;

    private String login;

    private String password;

    private String userName;

    private UserStatus state;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstNameCatUser() {
        return firstNameCatUser;
    }

    public void setFirstNameCatUser(String firstNameCatUser) {
        this.firstNameCatUser = firstNameCatUser;
    }

    public String getLastNameCatUser() {
        return lastNameCatUser;
    }

    public void setLastNameCatUser(String lastNameCatUser) {
        this.lastNameCatUser = lastNameCatUser;
    }
}
