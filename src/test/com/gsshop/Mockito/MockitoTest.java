package com.gsshop.Mockito;

import com.gsshop.Study.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // MockAnnotation을 써서 목을 만들 수 있습니다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MockitoTest {
    /*
        DAO나 REPOSITORY가 개발이 덜 되었을때도, 서비스를 개발할 수 있게 해주는 좋은 라이브러리입니다.
        UNIT TEST를 정의하는데 도 도움을 줍니다.
        단위테스트 - 마틴 파울러 -> 목을 사용하는 단위테스트는 어떻게 해야 할 것인가?
        외부 서비스는 내가 컨트롤을 할 수 없기 때문에 Mocking을 해줘야 한다고 생각합니다.
        스프링부트 2.2 이상에서는 모키토가 기본적으로 의존성에 추가가 됩니다. 근데 그게 없다면,
        JUNIT5랑 MOCKITO CORE , MOCKTIO JUNIT JUPYTER을 추가해줘야 합니다.

        Mock을 만드는 방법 1
        Mock이 어떻게 동작해야 하는지를 관리하는 방법 2
        Mock의 행동을 검증 하는 방법 3

        이렇게 3가지만 알면 Mockito를 잘 사용할 수 있다.

        Mock객체의 Stubbing

        Null을 리턴, Primitive는 기본 Primitive
        Optional인 경우 Optional.empty로 나옵니다.
        void는 아무일도 일어나지 않습니다.

     */

 //   Logger logger = LoggerFactory.getLogger(MockitoTest.class);

    @Test
    @DisplayName("StudyService를 생성합니다. - 방법 1")
    void createStudyService(){

        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }


    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("StudyService를 생성합니다. - Mock Annotation사용 방법 2.")
    void createStudyService2(){
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    @DisplayName("StudyService를 생성하는데, - Mock Annotation을 사용하는 방법 3.")
    void createStudyService3(@Mock MemberService service, @Mock StudyRepository repository){

        StudyService studyService = new StudyService(service, repository);
        //Argument matcher 입니다.
        //any() -> object
        //anyInt()
        //anyLong()
        //anyString()
        Member member = Member.builder().id(1L).email("blessdutch@naver.com").build();
        when(service.findById(anyLong())).thenReturn(Optional.of(member));
        Study study = new Study(10, "java");
        Study success = new Study(10, "java"); success.setOwner(member);
        when(repository.save(any())).thenReturn(success);
        when(service.findById(1L)).thenThrow(new RuntimeException()); //1이라는 값으로 들어오면, 런타임 익셉션을 처날리겠다.


        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

        //assertThrows로 해볼수 있습니다.

        Study savedStudy = studyService.createNewStudy(1L, study);

        assertEquals(savedStudy.getOwner().getId(), 1L, "같아야 합니다.");
        assertNotNull(studyService);
    }

    @Test
    void thirdTimeStubbing(){
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = Member.builder()
                .id(23397L)
                .email("ryu.hs@gsshop.com")
                .build();

        when(memberService.findById(anyLong()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        System.out.println("first argument");
        memberService.findById(3L).ifPresent(
                (m)->{
                    System.out.println(m.getId());
                    System.out.println(m.getEmail());
                }
        );

        System.out.println("second argument");

        assertThrows(RuntimeException.class, ()->{
            memberService.findById(20394L);
        });


        System.out.println("third argument");


        assertEquals(Optional.empty(), memberService.findById(2L));
    }

    @Test
    void mockObjectCheck(){


        //입증 하는 것입니다. notify가 진짜 1번 실행되었는지
        //argument matcher을 사용할 수 있습니다.
        Study study1 = new Study(10, "study1");
        Study study2 = new Study(20, "study2");
        memberService.notify(study1);
        memberService.notify(study1);
        memberService.notify(new Member());

        verify(memberService, times(1)).notify(study1);
        verifyNoMoreInteractions(memberService);



        //노티파이는 2번, 벨리데이트는 절대 일어나서는 안됩니다.
        assertAll(
                ()->verify(memberService, times(1)).notify(study1),
                ()->verify(memberService, never()).validate(anyLong())
        );

        //순서를 만드는 작업입니다.
        /*
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study1);
        inOrder.verify(memberService).notify(study2);
         */

    }


}
