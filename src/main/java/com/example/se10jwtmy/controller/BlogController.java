package com.example.se10jwtmy.controller;
//10
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin
public class BlogController {
    @GetMapping("/newMethod")
    public String newMethod(){
        return "HI Good Night !!!"; //return wenn one hi //403-access na
    }
}
