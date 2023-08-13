package com.example.repeat.contollers;

import com.example.repeat.entities.Role;
import com.example.repeat.entities.User;
import com.example.repeat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/avito/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String admin(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin-page";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam("userId") long id, @RequestParam Map<String, String> form){
        userService.edit(id, form);
        return "redirect:";
    }
    @PostMapping("/ban-unban")
    public String banUnban(@RequestParam("userId") long id){
        userService.banUnban(id);
        return "redirect:";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "edit-page";
    }
}
