package com.gsshop.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JavaFunctionalInterfaceTest {
    @Test
    void custom_functional_interface(){
        RunSomething runSomething = ()-> System.out.println("hyunsoo");
        IntSomething intSomething = (number)->number+10;
        runSomething.doIt();
        System.out.println(intSomething.doit(15));
    }


    @Test
    void implements_java_functional_interface(){
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));
    }


    @Test
    void java_functional_interface(){
        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        };
        System.out.println(function.apply(10));

        Function<Integer, Integer> functionLambda1 = (number)->{
            return number + 10;
        };

        System.out.println(functionLambda1.apply(100));

        Function<Integer, String> functionalLambda2 = number->{
            String tmp = "";
            for(int i = 0; i < number; i++){
                tmp += "good";
            }
            return tmp;
        };
        System.out.println(functionalLambda2.apply(10));

        ((Consumer<Integer>)((number)->{
            System.out.println(number);
        })).accept(100);

    }

    @Test
    void compose(){
        Function<Integer, Integer> plus5 = number->number+10;
        Function<Integer, Integer> mult5 = number->number*5;
        System.out.println(plus5.compose(mult5).apply(10));
        //compose뒤에 것을 먼저하고  이어서 합니다.
        System.out.println(plus5.andThen(mult5).apply(10));
        //andThen앞에 것을 먼저하고 이어서 합니다.
    }

    @Test
    void comsumer_T_(){
        Consumer<String> printString = System.out::println;
        printString.accept("good");
    }

    @Test
    void supplier_T_(){
        Supplier<String> getString = ()->"good";
        System.out.println(getString.get());
    }

    @Test
    void predicate_T_(){
        Predicate<String> startWithHyunsoo = str->str.startsWith("hyunsoo");
        System.out.println(startWithHyunsoo.negate().test("hyunsoos"));
    }

}