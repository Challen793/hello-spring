package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repositorry.MemberRepository;
import hello.hellospring.repositorry.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트가 끝날때 마다 저장소를 클리어
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //ID셋팅

        Member result = repository.findByID(member.getId()).get(); //Optional로 감싸진걸 가져올때 get()사용

        Assertions.assertThat(member).isEqualTo(result); //assertj의 Assertions
        assertThat(member).isEqualTo(result); //import static으로 Assertions 생략 가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //Shift + F6으로 변수명 동시에 바꾸기
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
