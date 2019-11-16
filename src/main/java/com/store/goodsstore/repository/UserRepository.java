package com.store.goodsstore.repository;

import com.store.goodsstore.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */
public interface UserRepository extends JpaRepository<Users, Integer> {

    public boolean existsByUserEmail(String email);

    public Users findByUserEmail(String email);    
    
}
