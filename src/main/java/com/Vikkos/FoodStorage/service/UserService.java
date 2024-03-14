package com.Vikkos.FoodStorage.service;

import com.Vikkos.FoodStorage.model.User;
import com.Vikkos.FoodStorage.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        repository.save(user);

    }


    //public Optional<User> findUserById(UUID user_id) { return repository.findById(user_id);}



    public Optional<User> findUserByUsername(String username) {
        return repository.findById(username);
    }
}