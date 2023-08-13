package com.example.repeat.contollers;

import com.example.repeat.entities.Avatar;
import com.example.repeat.entities.Image;
import com.example.repeat.services.AvatarService;
import com.example.repeat.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageController {
    private final ImageService imageService;
    private final AvatarService avatarService;
    @Autowired
    public ImageController(ImageService imageService, AvatarService avatarService) {
        this.imageService = imageService;
        this.avatarService = avatarService;
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> find(@PathVariable("id") long id){
        Image image = imageService.findById(id);
        return ResponseEntity.ok().header("imageOrigName", image.getOriginalName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @GetMapping("/avatar/{id}")
    public ResponseEntity<?> findAvatar(@PathVariable("id") long id){
        Avatar avatar = avatarService.findById(id);
        return ResponseEntity.ok().header("avatarOrigName", avatar.getOriginalName())
                .contentType(MediaType.valueOf(avatar.getContentType()))
                .contentLength(avatar.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));
    }
}
