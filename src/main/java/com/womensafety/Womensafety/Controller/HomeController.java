package com.womensafety.Womensafety.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greet(){
        return "welcome to 24/7 women safety";
    }
}
