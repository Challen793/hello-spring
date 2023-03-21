package hello.hellospring.repositorry;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //동시성 문제 있을 수 있음
    private static Long sequence = 0L; //동시성 문제 있을 수 있음

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //시스템에서 회원 id를 생성해주는 부분
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 null일 가능성이 있으면 Optional로 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //store에 있는 Member들에 대한 루프문
                .filter(member -> member.getName().equals(name)) //name을 가진 Member들을 필터링
                .findAny(); //하나라도 찾아지면 리턴, 없으면 Optional로 감싸져서 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
