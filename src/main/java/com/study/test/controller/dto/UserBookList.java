package com.study.test.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBookList {
    private String name;
    private int rentalCount;
}
