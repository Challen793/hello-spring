package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "TestPage!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    //public String helloMvc(@RequestParam(value = "name") String name, Model model){
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name){
        return "hello " + name; //단순 문자열 리턴
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 return
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
