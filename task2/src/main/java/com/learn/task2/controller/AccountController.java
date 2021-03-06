package com.learn.task2.controller;

import com.learn.task2.model.Account;
import com.learn.task2.model.AccountDto;
import com.learn.task2.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * body example
     *
       {
         "login": "user",
         "password": "password",
         "name": "postman",
         "age": 25,
         "role": "USER"
       }
     *
     */
    @PostMapping
    public Account save(@RequestBody AccountDto accountDto){
        return accountService.saveAccount(accountDto);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable long id) {
        return accountService.getAccountById(id);
    }

  /**
   * body example
   *
     {
       "id": 1,
       "login": "user",
       "password": "password",
       "name": "postman",
       "age": 25,
       "role": "USER"
     }
   *
   */
    @PutMapping
    public Account update(@RequestBody AccountDto accountDto) {
        return accountService.updateAccount(accountDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return accountService.deleteAccountById(id);
    }

}
