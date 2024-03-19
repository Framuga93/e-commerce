package ru.yarn.yarnstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/res")
    public String res(){
        return "Res";
    }

    @GetMapping
    public String auth(){
        return "Auth";
    }

    @GetMapping
    public String user(){
        return "User";
    }

    @GetMapping
    public String admin(){
        return "Admin";
    }

}
