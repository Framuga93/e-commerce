package gb.demochkin.ecommerce.controllers.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import gb.demochkin.ecommerce.dto.UserDto;
import gb.demochkin.ecommerce.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String users(Model model) {
        List<UserDto> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "users";
    }
}
