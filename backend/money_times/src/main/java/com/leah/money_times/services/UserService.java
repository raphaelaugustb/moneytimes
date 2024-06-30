package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.repository.UserRepository;
import com.leah.money_times.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void createUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(String id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
