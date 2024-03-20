package ru.yarn.yarnstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.UserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/views")
public class ViewController {

    private final UserRepository userRepository;
    @GetMapping("/res")
    public String res(){
        return "Res";
    }

    @GetMapping("/auth")
    public String auth(){
        return "Auth";
    }

    @GetMapping("/users")
    public List<User> user(){
        return userRepository.findAll();
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

}
