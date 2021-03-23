package com.gsshop.Study;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

/*
    JUNIT 4의 확장 모델은 @RunWith(Runner), TestRule, MethodRule
    JUNIT 5의 확장 모델은 단 하나, Extension 입니다.

 */

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ExtensionStudyRegisterExtension {

    @RegisterExtension
    FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1500L);

    @Test
    @DisplayName("현수의 테스트")
    void test_hyunsoo(){
        try {
            Thread.sleep(1600L);
        }catch(Exception e){}

        System.out.println("hyunsoo study");
    }

}
