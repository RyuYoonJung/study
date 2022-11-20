package com.study.test.service;

import com.study.test.controller.dto.TestResponse;
import com.study.test.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    public List<TestResponse> getTest() {
        return testMapper.findAll();
    }

    public void save(TestResponse response) {
        testMapper.save(response);
    }

    public void modified(TestResponse response) {
        testMapper.modified(response);
    }

    public void delete(Long id) {
        testMapper.delete(id);
    }

    public TestResponse findById(Long id) {
        return testMapper.findById(id);
    }
}


