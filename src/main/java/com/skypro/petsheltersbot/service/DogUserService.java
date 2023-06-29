package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.repository.DogUserRepositiry;
import org.springframework.stereotype.Service;

@Service
public class DogUserService {

    private final DogUserRepositiry dogUserRepositiry;

    public DogUserService(DogUserRepositiry dogUserRepositiry) {
        this.dogUserRepositiry = dogUserRepositiry;
    }
}
