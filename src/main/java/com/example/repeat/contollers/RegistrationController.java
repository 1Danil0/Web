package com.example.repeat.contollers;

import com.example.repeat.dto.UserDto;
import com.example.repeat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login-page";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration-page";
    }

    @PostMapping("/registration")
    public String registration(UserDto userDto, Model model){
        if(userService.save(userDto)){
            userService.save(userDto);
            return "redirect:/login";
        }
        model.addAttribute("error", "User is already exists");
        return "registration-page";
    }
}
