package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.repository.CatUserRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CatUserService {

    //private final Map<Long, CatUser> catUserMap = new HashMap<>();

    private final CatUserRepository catUserRepository;

    public CatUserService(CatUserRepository catUserRepository) {
        this.catUserRepository = catUserRepository;
    }


}
