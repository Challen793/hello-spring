package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //static폴더의 index.html(WelcomePage)보다 우선순위가 높다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
