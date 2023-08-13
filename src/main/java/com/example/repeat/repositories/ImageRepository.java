package com.example.repeat.repositories;

import com.example.repeat.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
