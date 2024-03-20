package ru.yarn.yarnstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/views")
public class ViewController {

    @GetMapping("/res")
    public String res(){
        return "Res";
    }

    @GetMapping("/auth")
    public String auth(){
        return "Auth";
    }

    @GetMapping("/user")
    public String user(){
        return "User";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

}
