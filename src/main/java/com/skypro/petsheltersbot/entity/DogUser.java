package com.skypro.petsheltersbot.entity;

import javax.persistence.*;


@Entity
@Table(name = "dog_users")
public class DogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
