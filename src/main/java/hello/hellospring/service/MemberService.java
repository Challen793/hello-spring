package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositorry.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //스프링이 어 얘는 서비스네 하고 빈으로 등록해줌
public class MemberService {

    private final MemberRepository memberRepository;

    //외부에서 memberRepository를 만들어서 넣어준다 > DI(Dependency Injection)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @input 멤버 객체
     * @return 가입 완료된 회원 ID
    */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    //ctrl+shift+m 명령어로 함수로 리팩토링
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return 멤버 리스트
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * ID를 통한 회원 조회
     * @return 멤버
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByID(memberId);
    }
}
