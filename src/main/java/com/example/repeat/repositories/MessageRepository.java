package com.example.repeat.repositories;

import com.example.repeat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findByTag(String tag);
}
