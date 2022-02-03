package com.learn.task2.service;

import com.learn.task2.model.Account;

public interface AccountService {

  Account saveAccount(String name, int age);

  Account getAccountById(long id);

  Account updateAccount(long id, String newName, int newAge);

  boolean deleteAccountById(long id);

}
