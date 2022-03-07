package com.learn.task2.repository;

import com.learn.task2.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Account findByLogin(String login);

}
