package com.skypro.petsheltersbot.repositories;

import com.skypro.petsheltersbot.entity.CatUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatUserRepository extends JpaRepository<CatUser, Long> {
    CatUser findCatUsersById(Long id);

}