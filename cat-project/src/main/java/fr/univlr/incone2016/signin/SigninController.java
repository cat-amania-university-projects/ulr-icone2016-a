package fr.univlr.incone2016.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping("signin")
    public String signin() {
        return "signin/signin";
    }
}
