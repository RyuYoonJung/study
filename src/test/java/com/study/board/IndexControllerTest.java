package com.study.board;

import com.study.test.controller.dto.TestResponse;
import com.study.test.service.TestService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
class IndexControllerTest {

    @Autowired
    TestService testService;

    @Test
    public void 글_등록() {
        TestResponse response = new TestResponse("윤정이1", "02");

        testService.save(response);

        TestResponse response2 = testService.findById(2l);
        assertThat(response.getName()).isEqualTo(response2.getName());
    }

    @Test
    public void 글_수정() {
        Long id = 7l;
        String name = "이름수정";
        String status = "00";

        TestResponse response = new TestResponse(id,name,status);

        testService.modified(response);

        TestResponse response2 = testService.findById(7l);
        assertThat(response.getName()).isEqualTo(response2.getName());
    }

    static class parameter {
        private String name;
        private String status;
    }

    @Test
    public void 글_삭제() {
        testService.delete(1l);
    }

    @Test
    public void 글_단일조회() {
        TestResponse response = testService.findById(2l);
        log.info(response.getName());

    }

    @Test
    public void 글_목록조회() {
        TestResponse response = new TestResponse("윤정이1", "02");

        testService.save(response);

        TestResponse response2 = testService.findById(2l);
        assertThat(response.getName()).isEqualTo(response2.getName());
    }

}