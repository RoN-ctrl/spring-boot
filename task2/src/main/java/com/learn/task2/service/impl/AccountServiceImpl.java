package com.learn.task2.service.impl;

import com.learn.task2.exception.AccountNotFoundException;
import com.learn.task2.model.Account;
import com.learn.task2.repository.AccountRepository;
import com.learn.task2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  AccountRepository accountRepository;

  @Override
  public Account saveAccount(String name, int age) {
    Account account = Account.builder().name(name).age(age).build();
    return accountRepository.save(account);
  }

  @Override
  public Account getAccountById(long id) {
    return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
  }

  @Override
  public Account updateAccount(long id, String newName, int newAge) {
    var user = getAccountById(id);
    user.setName(newName);
    user.setAge(newAge);
    return accountRepository.save(user);
  }

  @Override
  public boolean deleteAccountById(long id) {
    if (accountRepository.existsById(id)) {
      accountRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
