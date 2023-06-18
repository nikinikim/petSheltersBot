package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.entity.DogUser;
import com.skypro.petsheltersbot.repository.DogUserRepositiry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DogUserService {

//    private final Map<Long, DogUser> dogUserMap = new HashMap<>();

    private  final DogUserRepositiry dogUserRepositiry;

    public DogUserService(DogUserRepositiry dogUserRepositiry) {
        this.dogUserRepositiry = dogUserRepositiry;

    }

}





