package com.learn.task3;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.learn.task3.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Task3ApplicationTests {

  @Autowired
  AccountService accountService;

  @Test
  void contextLoads() {
    assertNotNull(accountService);
  }

}
