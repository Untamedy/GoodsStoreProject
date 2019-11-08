package com.store.goodsstore.config;

import com.store.goodsstore.entities.Previlages;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.store.goodsstore.repository.PrevilagesRepository;

/**
 *
 * @author YBolshakova
 */
//@Component
public class PrevilagesInit {

   /* @Autowired
    private PrevilagesRepository repositary;

    public static final String ADD_GOODS = "add";
    public static final String EDIT_GOODS = "edit";
    public static final String DELETE_GOODS = "delete";
    public static final String VIEW_GOODS = "view";
    public static Set<Previlages> admin = new HashSet<>();
    public static Set<Previlages> user = new HashSet<>();
    public static Set<Previlages> guest = new HashSet<>();

    public Set<Previlages> setOfAdminPrevilages() {
        return admin = addToList();
    }

    public Set<Previlages> setOfUserPrevilages() {
        user.add(createPrevilages(ADD_GOODS));
        user.add(createPrevilages(DELETE_GOODS));
        user.add(createPrevilages(VIEW_GOODS));
        return user;
    }

    public Set<Previlages> setOfGuestPrevilages() {
        guest = new HashSet<>();
        guest.add(createPrevilages(VIEW_GOODS));
        return guest;
    }

    public Set<Previlages> addToList() {
        Set<Previlages> setOfprevilageses = new HashSet<>();
        setOfprevilageses.add(createPrevilages(ADD_GOODS));
        setOfprevilageses.add(createPrevilages(EDIT_GOODS));
        setOfprevilageses.add(createPrevilages(DELETE_GOODS));
        setOfprevilageses.add(createPrevilages(VIEW_GOODS));
        return setOfprevilageses;
    }

    public Previlages createPrevilages(String name) {
        Previlages previlages = new Previlages();
        previlages.setName(name);
        return previlages;
    }

    @PostConstruct
    public void savePrevilageses() {
        Set<Previlages> previlagesList = addToList();
        List<Previlages> existPrevilages = repositary.findAll();
        for (Previlages p : previlagesList) {
            if (!existPrevilages.contains(p)) {
                repositary.save(p);
            }
        }
    }*/
}
