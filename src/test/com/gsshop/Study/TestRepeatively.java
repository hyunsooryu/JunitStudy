package com.gsshop.Study;


import com.gsshop.java8.ServiceLogic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestRepeatively {
   @RepeatedTest(value = 10)
    void repeat_test(){
       System.out.println("hello, world");
   }

   @ParameterizedTest(name = "{index} szVal={0}")
   @NullAndEmptySource
   @ValueSource(strings = {
           "날씨가",
           "많이",
           "추워지고",
           "있습니다."
   })
   void parameterized_test(String szVal){
       System.out.println(szVal);
   }

   @ParameterizedTest(name = "[{index}]->targetPhoneNumber => {0}")
   @ValueSource(strings = {
            "010-7499-8045","010-3236-7578","010-223323454"
   })
   void phoneCheckAlgorithmTest(String target){
       assertTrue(ServiceLogic.checkPhoneNumber(target));
   }

   @ParameterizedTest
   @ValueSource(ints = {10,20,40})
   void paremeter_test_int(Integer nVal){
      System.out.println(nVal);
   }

   @ParameterizedTest
   @ValueSource(ints = {10,20,40})
  // @CsvSource({"10, '자바 스터디'", "20, '정말 재밌어'"})
   void paremeter_test_study_1(@ConvertWith(StudyConverter.class) Study study){
      System.out.println(study.getLimit());
   }


   @ParameterizedTest
   @CsvSource({"10, 자바", "20, 굿"})
   void paremeter_test_study_2(ArgumentsAccessor argumentsAccessor){
      System.out.println(new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1)));
   }

   @ParameterizedTest
   @CsvSource({"10, 자바", "20, 굿"})
   void paremeter_test_study_3(int limit, String name){
      System.out.println(new Study(limit, name));
   }

   @ParameterizedTest
   @CsvSource({"10, 자바", "20, 굿"})
   void paremeter_test_study_4(@AggregateWith(StudyAggregator.class) Study study){
      System.out.println(study);
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/data.csv", delimiter = ';')
   void paremeter_test_csv_file(int limit, String name){
      assertTimeout(Duration.ofMillis(1000),()->{
         System.out.println(new Study(limit, name));
         Thread.sleep(2000);
      },()->"3초안에생성해야합니다.");
   }

   //애그리게이터는 static이거나, public 클래스 형태로 만들어져야 쓸수있곘죠???
   static class StudyAggregator implements ArgumentsAggregator{
      @Override
      public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
         return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
      }
   }

   static class StudyConverter extends SimpleArgumentConverter{
      @Override
      protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
         assertEquals(Study.class, aClass, "Can only convert to study");
         return new Study(Integer.parseInt(o.toString()));
      }
   }




}
