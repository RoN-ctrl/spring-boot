package com.learn.task1;

import static org.junit.jupiter.api.Assertions.*;

import com.learn.task1.service.TextService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Task1ApplicationTests {

	@Autowired
	TextService textService;

	@Test
	void contextLoads() {
		assertNotNull(textService);
	}

}
