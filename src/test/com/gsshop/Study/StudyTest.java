package com.gsshop.Study;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @Test
    @Tag("fast")
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study(10);
        assertNotNull(study);
        assertEquals(study.getStatus(), StudyStatus.DRAFT, () ->
                "스터디를 처음 생성하면 상태값이 DRAFT 여야합니다."
        );
    }

    @Test
    @Tag("slow")
    @DisplayName("스터디 모두 테스트 해보기")
    void assert_all() {
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study, ()->"스터디는 널이 아니어야 합니다."),
                () -> assertEquals(study.getStatus(), StudyStatus.ENDED, "스터디의 초시 상태는 드래프트 여야 합니다."),
                () -> assertTrue(study.getLimit() > 10, () -> "스터디의 리밋은 10 이상이어야 합니다.")
        );
    }

    @Test
    @Tag("fast")
    @DisplayName("스터디 쓰로우 해보기")
    void test_exception(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->new Study(-10));
        String message = exception.getMessage();
        assertEquals(message, "limit은 0 보다 커야 합니다.");
    }

    @Test
    @Tag("slow")
    @DisplayName("테스트의 시간을 측정해보기")
    void test_timeout(){
        assertTimeout(Duration.ofSeconds(10), ()->{
            new StudyTest();
            Thread.sleep(30000); //30초
        });
    }

    //ThreadLocal을 활용하는 코드가 있다. 이 블럭에? 그럴경우에는 예상하지 못한 결과가 나타날 수 있습니다.
    @Test
    @Tag("slow")
    @DisplayName("테스트의 시간을 측정해보기 - 오버시 즉각적으로 종료")
    void test_timeout_preemptively(){
        assertTimeoutPreemptively(Duration.ofSeconds(10), ()->{
            new StudyTest();
            Thread.sleep(30000); //30초
        });
    }

    @Test
    @Tag("fast")
    @DisplayName("assumtion을 알아보자")
    void assume_True(){
        assumeTrue(2 == 2, "good");
    }

    @FastTest
    void custom_tag_test(){
        System.out.println("custom tag로 만들어진 테스트입니다.");
    }






}