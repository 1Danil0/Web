package com.example.repeat.services;

import com.example.repeat.dto.UserDto;
import com.example.repeat.entities.Role;
import com.example.repeat.entities.User;
import com.example.repeat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean save(UserDto userDto) {
        User user;
        if(userRepository.findByLogin(userDto.getLogin()) != null ||
                userRepository.findByEmail(userDto.getEmail()) != null){
            return false;
        }
        user = new User();
        user.setActive(true);
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNameSurname(userDto.getNameSurname());
        user.addRole(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new NoSuchElementException("no user");
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void banUnban(long id){
        User user = userRepository.findById(id).get();
        if(user.isEnabled()==true){
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        userRepository.save(user);
    }
    public void edit(long id, Map<String, String> form){
        User user = findById(id);
        user.getRoles().clear();
        Set<String> roles = Arrays.stream(Role.values())
                .map(el -> el.name()).collect(Collectors.toSet());
        for(String key: form.keySet()){
            if(roles.contains(key)){
                user.addRole(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
