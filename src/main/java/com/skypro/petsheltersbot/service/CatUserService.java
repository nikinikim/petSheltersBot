package com.skypro.petsheltersbot.service;

import com.skypro.petsheltersbot.entity.CatUser;
import com.skypro.petsheltersbot.repository.CatUserRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CatUserService {

    private final Map<Long, CatUser> catUserMap = new HashMap<>();

    private final CatUserRepository catUserRepository;

    public CatUserService(CatUserRepository catUserRepository) {
        this.catUserRepository = catUserRepository;
    }


//    NotificationTask notificationTask = new NotificationTask();
//                                    notificationTask.setChatID(chatID);
//                                    notificationTask.setMessage(txt);
//                                    notificationTask.setNotificationDateTime(dateTime);
//                                    notificationTaskService.save(notificationTask);
//    sendMessage(chatID, "Задача успешно запланирована!");

    public void addCatLogin(Long userID, String login) {
        CatUser catUser = new CatUser();

    }

    public void addCatPassword(Long userID, String password) {
        CatUser catUser = catUserRepository.save(new CatUser());

    }


    public void addruleDating(Long dataID, String ruleDating) {
        CatUser catUser = new CatUser();
        catUser.ruleDating = ruleDating;
        catUserMap.put(dataID, catUser);
    }

    public void adddocuments(Long dataID, String documents) {
        CatUser catUser = new CatUser();
        catUser.documents = documents;
        catUserMap.put(dataID, catUser);
    }

    public void addTransportirationRules(Long dataID, String transportirationRules) {
        CatUser catUser = new CatUser();
        catUser.transportirationRules = transportirationRules;
        catUserMap.put(dataID, catUser);
    }

    public void addPlacementRulesLittle(Long dataID, String placementRulesLittle) {
        CatUser catUser = new CatUser();
        catUser.placementRulesLittle=placementRulesLittle;
        catUserMap.put(dataID, catUser);
    }

    public void addPlacementRulesBig(Long dataID, String placementRulesBig) {
        CatUser catUser = new CatUser();
        catUser.placementRulesBig=placementRulesBig;
        catUserMap.put(dataID, catUser);
    }

    public void addSpecialPlacementRules(Long dataID, String specialPlacementRules) {
        CatUser catUser = new CatUser();
        catUser.specialPlacementRules=specialPlacementRules;
        catUserMap.put(dataID, catUser);
    }

    public void addReasonRefused(Long dataID, String reasonRefused) {
        CatUser catUser = new CatUser();
        catUser.reasonRefused=reasonRefused;
        catUserMap.put(dataID, catUser);
    }

    public void save(CatUser catUser){
       catUserRepository.save(catUser);
    }

}
