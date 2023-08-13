package com.example.repeat.services;

import com.example.repeat.entities.Image;
import com.example.repeat.entities.Message;
import com.example.repeat.entities.User;
import com.example.repeat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findByTag(String tag) {
        if (tag == null || tag.isEmpty() || tag.isBlank()) {
            return messageRepository.findAll();
        }
        return messageRepository.findByTag(tag);
    }

    public Message findById(long id) throws NoSuchElementException {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        }
        throw new NoSuchElementException("no element");
    }

    public void deleteById(long id) {
        messageRepository.deleteById(id);
    }

    public void save(Message message, Principal principal, MultipartFile... files) throws IOException {
        if (files != null) {
            for (MultipartFile file : files) {
                if (file.getSize() != 0) {
                    Image image = Image.toImage(file);
                    message.addImage(image);
                    if (message.getImages().stream().filter(img -> img.isPreview() == true)
                            .collect(Collectors.toSet()).isEmpty()) {
                        image.setPreview(true);
                        message.addImage(image);
                    }
                    Message messageToPreview = messageRepository.save(message);
                    message.setPreviewImage(messageToPreview.getImages().stream().filter(img -> img.isPreview() == true)
                            .findFirst().get().getId());
                }
            }
            User user = findUserByPrincipal(principal);
            message.setUser(user);
            messageRepository.save(message);
        }


    }
    public User findUserByPrincipal(Principal principal){
        if(principal == null){
            return new User();
        }
        return userService.findByLogin(principal.getName());
    }
}

