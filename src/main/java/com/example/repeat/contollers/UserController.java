package com.example.repeat.contollers;

import com.example.repeat.entities.User;
import com.example.repeat.services.AvatarService;
import com.example.repeat.services.ImageService;
import com.example.repeat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/avito/users")
public class UserController {
    private final ImageService imageService;
    private final UserService userService;
    private final AvatarService avatarService;
    @Autowired
    public UserController(ImageService imageService, UserService userService, AvatarService avatarService) {
        this.imageService = imageService;
        this.userService = userService;
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id")long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("messages", userService.findById(id).getMessages());
        return "user-page";
    }
    @GetMapping("/my")
    public String myPage(Model model, Principal principal){
        User user = userService.findByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("messages", user.getMessages());
        return "my-page";
    }
    @PostMapping("/my/addPhoto")
    public String addPhoto(@RequestParam("photo")MultipartFile photo, Principal principal) throws IOException {
        avatarService.save(photo, principal);
        return "redirect:/avito/users/my";
    }

}
