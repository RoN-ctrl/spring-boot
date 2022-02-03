package com.learn.task2.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.learn.task2.exception.AccountNotFoundException;
import com.learn.task2.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceImplTest {

  @Autowired
  AccountService accountService;

  @Test
  void saveAndGetAccountTest() {
    var account = accountService.saveAccount("TestName", 28);
    assertEquals(account, accountService.getAccountById(account.getId()));
  }

  @Test
  void updateAccount() {
    var account = accountService.saveAccount("TestName", 28);
    assertEquals("TestName", account.getName());
    assertEquals(28, account.getAge());

    var updatedAccount = accountService.updateAccount(account.getId(), "NewName", 32);
    var accountFromDB = accountService.getAccountById(account.getId());
    assertEquals(updatedAccount.getName(), accountFromDB.getName());
    assertEquals(updatedAccount.getAge(), accountFromDB.getAge());
  }

  @Test
  void deleteAccountById() {
    var account = accountService.saveAccount("TestName", 28);
    long id = account.getId();
    assertNotNull(accountService.getAccountById(id));

    accountService.deleteAccountById(id);
    assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(id));
  }
}