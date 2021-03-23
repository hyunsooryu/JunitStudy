package com.gsshop.Study;


import org.junit.jupiter.api.*;


//@TestInstance(value = TestInstance.Lifecycle.PER_METHOD)  -> 기본 전략
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //-> 새로운 전략 / 클래스에 하나의 인스턴스를 만듬
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 메소드의 순서를 불어넣습니다.
public class TestInstance_ {
    int value = 0;
    /*
        테스트 간의 의존성을 없애기 위해,
        테스트 마다 인스턴스를 생성하는 정책을 유지해 왔습니다.
        기본 전략입니다.

        JUNIT5에서는, 매번 테스트 인스턴스를 만들지 않는 정책도 추가되었습니다.

        테스트의 순서는 어떻게 될까요?

        순서대로 실행되는게 좋지 않을까요?

        순서에 의존해서는 안됩니다.

        순서는 언제라도 바뀔 수 있어서,

        순서를 명확히 정해도됩니다. @Order

     */

    //TestInstance.LifeCycle.PER_CLASS에서는 static method -> method 로 만들어줘도 됩니다.


    @BeforeAll()
    void beforeAll(){
        System.out.println("before All");
        System.out.println(this);
    }

    @Test
    @Order(2)
    void test1(){
        System.out.println("i am test1");
        System.out.println(value++);
        System.out.println(this);
    }

    @Test
    @Order(1)
    void test2(){
        System.out.println("i am test2");
        System.out.println(value++);
        System.out.println(this);
    }

}
