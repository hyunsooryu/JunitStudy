package com.gsshop.Mockito;

import com.gsshop.Study.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BddTest {
    /**
     * BDD : 애플리케이션이 어떻게 행동해야 하는지에 대한 공통된 이해를 구성하는 방법으로, TDD에서 창안했습니다.
     * Behavior Driven Development
     * Title
     * Narrative -> As a / I want / so that
     * Acceptance criteria -> Given / When / Then
     */

    @Mock
    private MemberService memberService;

    @Mock
    private StudyRepository studyRepository;

    @Test
    @DisplayName(value = "Behavior Driven Development 테스트 방법")
    void BddTest(){

        //GIVEN
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = Member.builder().id(20132513L).email("blessdutch@naver.com").build();
        Study study = new Study(10, "study1");

        //when(memberService.findById(1L)).thenReturn(Optional.of(member));
        //when(studyRepository.save(study)).thenReturn(study);

        //BDD 스타일입니다.
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);



        //WHEN
        studyService.createNewStudy(1L, study);
        memberService.notify(study);

        //THEN
       // assertEquals(member, study.getOwner());
       // verify(memberService, times(0)).notify(study);
       // verifyNoMoreInteractions(memberService);

        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();
    }

}
