package com.example.repeat.services;

import com.example.repeat.entities.Avatar;
import com.example.repeat.entities.User;
import com.example.repeat.repositories.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserService userService;
    @Autowired
    public AvatarService(AvatarRepository avatarRepository, UserService userService) {
        this.avatarRepository = avatarRepository;
        this.userService = userService;
    }

    public void save(MultipartFile file, Principal principal) throws IOException {
        Avatar avatar = Avatar.toImage(file);
        User user = userService.findByLogin(principal.getName());
        if(user.getAvatar() != null){
            long id = user.getAvatar().getId();
            user.setAvatar(null);
            avatarRepository.deleteById(id);
        }
        avatar.setUser(user);
        user.setAvatar(avatar);
        avatarRepository.save(avatar);
    }


    public Avatar findById(long id){
        Optional<Avatar> avatar = avatarRepository.findById(id);
        if(avatar.isPresent()){
            return avatar.get();
        }
        throw new NoSuchElementException("no avatar");
    }
}
