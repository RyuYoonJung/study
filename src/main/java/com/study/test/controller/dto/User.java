package com.study.test.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private int rentalCount; //5권 이상이면 대여불가
    private String status; //00 대여가능 01 대여 불가능
    private String grade; // 01 -> 05 단계별 할인율 5퍼씩 누적 00은 할인 없음
    private List<UserBookList> bookList;
}
