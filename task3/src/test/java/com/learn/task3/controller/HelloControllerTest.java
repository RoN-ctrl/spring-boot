package com.learn.task3.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class HelloControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void helloTest() throws Exception {
    mvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Hello"));
  }

  @Test
  void userTest() throws Exception {
    mvc.perform(get("/user"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("User"));
  }

  @Test
  void adminTest() throws Exception {
    mvc.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Admin"));
  }
}