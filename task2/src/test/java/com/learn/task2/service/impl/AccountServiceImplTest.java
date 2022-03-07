package com.learn.task2.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.learn.task2.exception.AccountNotFoundException;
import com.learn.task2.model.AccountDto;
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
    var account = accountService.saveAccount(createTestAccountDto());
    assertEquals(account, accountService.getAccountById(account.getId()));
  }

  @Test
  void updateAccount() {
    var account = accountService.saveAccount(createTestAccountDto());
    assertEquals("TestName", account.getName());
    assertEquals(28, account.getAge());

    var accountDto = AccountDto.builder().id(account.getId()).name("NewName").age(32).build();
    var updatedAccount = accountService.updateAccount(accountDto);
    var accountFromDB = accountService.getAccountById(account.getId());
    assertEquals(updatedAccount.getName(), accountFromDB.getName());
    assertEquals(updatedAccount.getAge(), accountFromDB.getAge());
  }

  @Test
  void deleteAccountById() {
    var account = accountService.saveAccount(createTestAccountDto());
    long id = account.getId();
    assertNotNull(accountService.getAccountById(id));

    accountService.deleteAccountById(id);
    assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(id));
  }

  private AccountDto createTestAccountDto() {
    return AccountDto.builder()
            .name("TestName")
            .age(28)
            .build();
  }
}