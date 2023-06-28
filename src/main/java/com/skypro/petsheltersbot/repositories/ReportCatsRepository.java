package com.skypro.petsheltersbot.repositories;

import com.skypro.petsheltersbot.entity.Cats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCatsRepository extends JpaRepository<Cats, Long> {
}
