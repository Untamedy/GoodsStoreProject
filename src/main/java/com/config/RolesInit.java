package com.config;

import com.entities.Previlages;
import com.entities.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.repository.RolesRepository;

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
        for (Role r : setOfRoles) {
            if (!existRoles.contains(r)) {
                repositary.save(r);
            }

        }
    }

    public Set<Role> addRolesToList() {
        Set<Role> roles = new HashSet<>();
        roles.add(createRole(ROLE_ADMIN, PrevilagesInit.admin));
        roles.add(createRole(ROLE_USER, PrevilagesInit.user));
        roles.add(createRole(ROLE_GUEST, PrevilagesInit.guest));
        return roles;
    }

    public Role createRole(String name, Set<Previlages> previlageses) {
        Role role = new Role();
        role.setRoleName(name);
        role.setPrevilagesName(previlageses);
        return role;

    }

}
