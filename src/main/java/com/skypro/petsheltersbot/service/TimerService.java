package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.entity.InfoMessage;
import com.skypro.petsheltersbot.repository.TimerRepository;
import org.springframework.stereotype.Service;

@Service
public class TimerService {
    private final TimerRepository timerRepository;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }


    public void save(InfoMessage infoMessage) {
        timerRepository.save(infoMessage);
    }
}
