package com.example.repeat.repositories;

import com.example.repeat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    public User findByLogin(String login);
}
