package com.skypro.petsheltersbot.entity;

import javax.persistence.*;


@Entity
@Table(name = "cat_users")
public class CatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstNameCatUser;

    @Column
    private String lastNameCatUser;

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
