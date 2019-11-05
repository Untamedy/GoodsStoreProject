package com.repository;

import com.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author YBolshakova
 */
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users existsByUserEmail(String email);

    public Users findByUserEmail(String email);
    
    //@Query("select * from Users")
    public Page<Users> findAll(Pageable page);

}
