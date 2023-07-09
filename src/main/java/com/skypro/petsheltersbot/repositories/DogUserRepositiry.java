package com.skypro.petsheltersbot.repositories;

import com.skypro.petsheltersbot.entity.DogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DogUserRepositiry extends JpaRepository<DogUser, Long> {
    DogUser findDogUsersById(Long id);

}
