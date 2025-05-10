package com.example.KitchEase.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.KitchEase.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findById(String userId);
    Optional<User> findByUserName(String userName);
  
    boolean existsById(String userId);
    void deleteById(String userId);


    
}