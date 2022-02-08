package com.learn.task3.service;

import com.learn.task3.model.Account;
import com.learn.task3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Account account = accountRepository.findByLogin(userName);
    if (account == null) {
      throw new UsernameNotFoundException("Unknown user: " + userName);
    }
    return User.builder()
        .username(account.getLogin())
        .password(account.getPassword())
        .roles(account.getRole())
        .build();
  }
}
