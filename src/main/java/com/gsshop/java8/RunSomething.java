package com.gsshop.java8;

//함수형 인터페이스입니다.
@FunctionalInterface
public interface RunSomething {
    void doIt(); //추상 메소드가 하나라면, -> functionalInterface입니다.

    static void printName(){
        System.out.println("Hyunsoo");
    }

    default void printAge(){
        System.out.println("29");
    }
}
