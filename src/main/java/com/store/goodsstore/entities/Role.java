package com.store.goodsstore.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author YBolshakova
 */
@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "my_seq", initialValue = 1, allocationSize = 1)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private int id;
    @Column(unique = true)
    private String roleName;

    public Role(String name) {
        this.roleName = name;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

}
