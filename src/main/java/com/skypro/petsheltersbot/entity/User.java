package com.skypro.petsheltersbot.entity;

import javax.persistence.*;


@Entity
@Table(name = "animal_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstNameCatUser;

    @Column
    private String lastNameCatUser;

    @Column
    private String firstNameDogUser;

    @Column
    private String lastNameDogUser;

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