package com.example.KitchEase.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.KitchEase.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
    Optional<User> findById(String userName);
  
    boolean existsById(String userName);
    void deleteById(String userName);


    
}