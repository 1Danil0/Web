package com.example.repeat.contollers;

import com.example.repeat.entities.Message;
import com.example.repeat.services.ImageService;
import com.example.repeat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/avito/main")
public class MainController {
    private final MessageService messageService;
    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String findAll(@RequestParam(value = "tag", required = false) String tag,
                          @RequestParam(name = "cities", defaultValue = "", required = false) String city,
                          Model model, Principal principal){
        model.addAttribute("messages", messageService.findByTagAndCity(tag, city));
        model.addAttribute("user", messageService.findUserByPrincipal(principal));
        model.addAttribute("cities", city);
        model.addAttribute("tag", tag);
        return "main-page";
    }
    @GetMapping("/message/{messageId}")
    public String findOne(@PathVariable("messageId") long id, Model model){
        Message message = messageService.findById(id);
        model.addAttribute("message", message);
        model.addAttribute("images", message.getImages());
        return "one-message";
    }
    @PostMapping("/add")
    public String add(@RequestParam("file1") MultipartFile file1,
                      @RequestParam("file2") MultipartFile file2,
                      @RequestParam("file3") MultipartFile file3,
                      Message message, Principal principal) throws IOException {
        MultipartFile[] files = {file1, file2, file3};
        messageService.save(message, principal, files);
        return "redirect:/avito/users/my";
    }
    @PostMapping("/my/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id")long id){
        messageService.deleteById(id);
        return "redirect:/avito/users/my";
    }
}
