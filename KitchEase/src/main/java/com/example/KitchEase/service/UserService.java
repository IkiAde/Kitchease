package com.example.KitchEase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KitchEase.Interface.UserInterface;
import com.example.KitchEase.entity.User;
import com.example.KitchEase.repository.UserRepository;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void createUser(String userName, String firstName, String lastName, String email, String password,
            String unit, String access) {
       var user = new User(userName, firstName, lastName, email, password, unit, access);
       userRepo.save(user);
    }


   
    public Optional<User> findById(String userName) {
        return userRepo.findById(userName);
    }

    public boolean existsById(String userName) {
       return userRepo.existsById(userName);
    }

    @Override
    public void deleteById(String userName) {
        userRepo.deleteById(userName);
    }

    public Iterable<User> findAllUsers() {
       return userRepo.findAll();
    }





}
