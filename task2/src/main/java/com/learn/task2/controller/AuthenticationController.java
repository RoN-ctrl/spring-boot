package com.learn.task2.controller;

import com.learn.task2.security.AuthRequest;
import com.learn.task2.security.AuthResponse;
import com.learn.task2.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JWTUtil jwtTokenUtil;

  /**
   * body example
   *
     {
         "login": "login",
         "password": "password"
     }
   *
   */
  @PostMapping("/authenticate")
  @ResponseStatus(HttpStatus.OK)
  public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {
    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
      log.info(authentication.toString());
    } catch (BadCredentialsException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect login or password", e);
    }
    String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

    return new AuthResponse(jwt);
  }
}
