package hello.hellospring.Controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //스프링이 어 얘는 컨트롤러네 하고 빈으로 등록해줌
public class MemberController {
    //다른 컨트롤러에서도 이 memberService를 가져다 쓸건데, 각 컨트롤러마다 다 하나씩 new해서 들고있을 필요가 있을까?
    //private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    //최초 스프링이 MemberController를 생성할때 해당 생성자를 호출할건데 @Autowired 붙어있으면 MemberService를 하나 만들어서 넣어줌 (스프링이 자동으로 연결시켜준다, DI 주입해준다)
    //근데 스프링에서는 MemberService를 모르는 상태 → 서비스 쪽에 @Service를 붙여준다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}