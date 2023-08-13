package com.example.repeat.services;

import com.example.repeat.entities.Image;
import com.example.repeat.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image findById(long id){
        Optional<Image> image = imageRepository.findById(id);
        if(image != null) {
            return image.get();
        }
        throw new NoSuchElementException("no image");
    }


}
