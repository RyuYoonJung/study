package com.study.test.controller;

import com.study.test.controller.dto.TestResponse;
import com.study.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public List<TestResponse> getTest() {
        return testService.getTest();
    }
}
