package com.learn.task2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.learn.task2.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Task2ApplicationTests {

  @Autowired
  AccountService accountService;

  @Test
  void contextLoads() {
    assertNotNull(accountService);
  }

}
