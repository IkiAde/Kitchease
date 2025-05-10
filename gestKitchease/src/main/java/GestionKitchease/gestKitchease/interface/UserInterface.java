package com.example.KitchEase.Interface;
import java.util.Optional;
import com.example.KitchEase.entity.User;

public interface UserInterface {

    void createUser(String userName, String firstName, String lastName, String email, String password, String unit, String access);
    void deleteById(String userId);


}
