package com.learn.task3.service;

import com.learn.task3.exception.AccountNotFoundException;
import com.learn.task3.model.Account;
import com.learn.task3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Account getById(long id) {
    return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
  }

  public boolean deleteById(long id) {
    if (accountRepository.existsById(id)) {
      accountRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
