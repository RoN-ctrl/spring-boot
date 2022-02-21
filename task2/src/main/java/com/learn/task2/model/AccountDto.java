package com.learn.task2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private long id;
    private String name;
    private int age;
}
