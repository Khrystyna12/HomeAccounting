package com.home.accounting.repository;

import com.home.accounting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select account.id, amount, title, user_entity_id " +
            "from account inner join user_entity on user_entity_id = user_entity.id where email = ?1", nativeQuery = true)
    List<Account> getByUserEmail(String email);
}
