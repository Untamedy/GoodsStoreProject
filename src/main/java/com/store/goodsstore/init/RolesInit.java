package com.store.goodsstore.init;

import com.store.goodsstore.entities.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.store.goodsstore.repository.RolesRepository;

/**
 *
 * @author YBolshakova
 */
@Component
public class RolesInit {

    @Autowired
    private RolesRepository repositary;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";
    public static final String ROLE_GUEST = "guest";

    @PostConstruct
    public void saveRoles() {
        Set<Role> setOfRoles = addRolesToList();
        List<Role> existRoles = repositary.findAll();
        if (!existRoles.retainAll(setOfRoles)) {
            setOfRoles.removeAll(existRoles);
            setOfRoles.forEach((r) -> {
                repositary.save(r);
            });
        }
    }

    public Set<Role> addRolesToList() {
        Set<Role> roles = new HashSet<>();
        roles.add(createRole(ROLE_ADMIN));
        roles.add(createRole(ROLE_USER));
        roles.add(createRole(ROLE_GUEST));
        return roles;
    }

    public Role createRole(String name) {
        Role role = new Role(name);

        return role;

    }

}
