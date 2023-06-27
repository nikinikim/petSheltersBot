package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.repository.DogUserRepositiry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DogUserService {

    private final Map<Long, DogUser> dogUserMap = new HashMap<>();

    private  final DogUserRepositiry dogUserRepositiry;

    public DogUserService(DogUserRepositiry dogUserRepositiry) {
        this.dogUserRepositiry = dogUserRepositiry;

    }
    public void addLogin(Long userID, String login) {
        DogUser user = new DogUser();
        user.login = login;
        dogUserMap.put(userID, user);
    }

    public void addPassword(Long userID, String password) {
        DogUser user = dogUserMap.get(userID);
        user.password = password;
    }
    public DogUser getUser(Long id) {
        return dogUserMap.get(id);
    }

}





