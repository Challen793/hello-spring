package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositorry.MemberRepository;
import hello.hellospring.repositorry.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트 수행 후 rollback을 수행 함
class MemberServiceIntegrationTest {
    /*
    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        //memberService 내부의 memberRepository를 여기서 직접생성하여 넣어줄 것이다.
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }*/

    //보통은 생성자를 통해 주입 받아야하지만, 테스트에서는 그냥 편하게 아래와 같이 쓴다
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given - 주어진 데이터
        Member member = new Member();
        member.setName("hello");

        //when - 어떠한 상황이 주어졌을 때
        Long saveId =  memberService.join(member);

        //then - 이런 결과값이 나와야한다
        Member findMember = memberService.findOne(member.getId()).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        //특정 Exception이 발생하는지를 검증
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //예외 메세지를 검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}