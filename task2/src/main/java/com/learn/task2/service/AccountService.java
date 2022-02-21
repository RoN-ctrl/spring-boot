package com.learn.task2.service;

import com.learn.task2.model.Account;
import com.learn.task2.model.AccountDto;

public interface AccountService {

  Account saveAccount(AccountDto accountDto);

  Account getAccountById(long id);

  Account updateAccount(AccountDto accountDto);

  boolean deleteAccountById(long id);

}
