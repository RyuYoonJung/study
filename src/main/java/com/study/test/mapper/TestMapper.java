package com.study.test.mapper;

import com.study.test.controller.dto.TestResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestResponse> findAll();

    void save(TestResponse response);

    void modified(TestResponse response);

    void delete(Long id);

    TestResponse findById(Long id);

}
