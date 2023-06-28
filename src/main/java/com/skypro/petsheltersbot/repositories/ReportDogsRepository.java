package com.skypro.petsheltersbot.repositories;

import com.skypro.petsheltersbot.entity.Dogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDogsRepository extends JpaRepository<Dogs, Long> {
}
