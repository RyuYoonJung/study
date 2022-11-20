package com.study.test.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TestResponse extends timeResponse{
    private Long id;
    private String name;
    private String status;

    public TestResponse(String name, String status) {
        this.name = name;
        this.status = status;
    }

    @Builder
    public TestResponse(Long id , String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
