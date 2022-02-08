package com.learn.task3;

import com.learn.task3.model.Account;
import com.learn.task3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task3Application implements CommandLineRunner {

  @Autowired
  AccountService accountService;

  public static void main(String[] args) {
    SpringApplication.run(Task3Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Account user = Account.builder().login("user").password("pass").role("USER").build();
    Account admin = Account.builder().login("admin").password("pass").role("ADMIN").build();

    accountService.save(user);
    accountService.save(admin);
  }
}
