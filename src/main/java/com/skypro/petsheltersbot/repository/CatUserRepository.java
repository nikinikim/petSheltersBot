package com.skypro.petsheltersbot.repository;

import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.entity.Cats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




public interface CatUserRepository extends JpaRepository<CatUser, Long> {

    CatUser findCatUsersById(Long id);

}
