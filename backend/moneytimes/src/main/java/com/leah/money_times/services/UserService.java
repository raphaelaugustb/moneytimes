package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.exception.InvalidRequestException;
import com.leah.money_times.exception.UserNotFoundException;
import com.leah.money_times.repository.UserRepository;
import com.leah.money_times.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

     UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Verificar usuário
    public User verifyUser(String userId) {
     return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void createUser(UserRequest userRequest) {
        User user = new User();
        if (userRequest.username() == null || userRequest.email() == null || userRequest.password() == null)
            throw new InvalidRequestException("Invalid user request: Missing parameters");
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
        user.setEmail(userRequest.email());
        user.setIncomesList(new ArrayList<>());
        user.setBillsList(new ArrayList<>());
        user.setTransactionList(new ArrayList<>());
        userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = verifyUser(userId);
        userRepository.delete(user);
    }

    public User getUserInfo(String id) {
        return verifyUser(id);
    }

    public void updateUserInfo(String id, UserRequest userRequest) {
        User user = verifyUser(id);

        if (userRequest.username() == null || userRequest.email() == null || userRequest.password() == null)
            throw new InvalidRequestException("Invalid user request: Missing parameters");
        user.setUsername(userRequest.email());
        user.setPassword(userRequest.password());
        user.setUsername(userRequest.username());
        userRepository.save(user);

    }

}
