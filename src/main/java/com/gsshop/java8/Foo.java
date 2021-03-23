package com.gsshop.java8;

public class Foo {
    public static void main(String[] args) {
        //익명 내부 클래스 -> 기존의 방법이라면
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hi");
            }
        };

        RunSomething runSomething1 = ()->System.out.println("Hello");

        //runSomething1.doIt();

        play(runSomething);
        play(runSomething1);

        IntSomething intSomething = (number)->number+10;

        System.out.println(intSomething.doit(10));
        System.out.println(intSomething.doit(100));
    }

    static void play(RunSomething runSomething){
        runSomething.doIt();
    }
}
