package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.repository.UserRepository;
import com.leah.money_times.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Bidi;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //Verificar usuário
    public User verifyUser(String userId){{
        User user = userRepository.findById(userId).get();
        if ((user != null)){
            throw new NullPointerException("Usuário invalido");
        } else {
            return user;
        }
    }
    }
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
    public User getUserInfo(String id){
        User user = verifyUser(id);
        return user;
    }
    public void updateUserInfo(String id, UserRequest userRequest){
        User user = verifyUser(id);

        if (userRequest.getUsername() != null){
            user.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null){
            user.setPassword(userRequest.getPassword());
        }
        userRepository.save(user);

    }

}
