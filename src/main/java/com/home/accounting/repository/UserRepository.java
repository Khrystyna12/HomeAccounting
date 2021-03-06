package com.home.accounting.repository;

import com.home.accounting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user_entity where email =?1", nativeQuery = true)
    User getUserByEmail(String email);
}
