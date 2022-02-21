package com.learn.task2.service.impl;

import com.learn.task2.exception.AccountNotFoundException;
import com.learn.task2.model.Account;
import com.learn.task2.model.AccountDto;
import com.learn.task2.repository.AccountRepository;
import com.learn.task2.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public Account saveAccount(AccountDto accountDto) {
    return accountRepository.save(mapDtoToAccount(accountDto));
  }

  @Override
  public Account getAccountById(long id) {
    return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
  }

  @Override
  public Account updateAccount(AccountDto accountDto) {
    Account account = getAccountById(accountDto.getId());
    account.setName(account.getName());
    account.setAge(account.getAge());
    return accountRepository.save(account);
  }

  @Override
  public boolean deleteAccountById(long id) {
    if (accountRepository.existsById(id)) {
      accountRepository.deleteById(id);
      return true;
    }
    return false;
  }

  private Account mapDtoToAccount(AccountDto accountDto) {
    return Account.builder()
            .name(accountDto.getName())
            .age(accountDto.getAge())
            .build();
  }
}
