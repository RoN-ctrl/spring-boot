package com.learn.task1.service;

import org.springframework.stereotype.Service;

@Service
public class TextService {

  public static final String HELLO_WORLD = "Hello world!";

  public String printHelloWorld() {
    return HELLO_WORLD;
  }
}
