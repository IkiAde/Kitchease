// package GestionKitchease.gestKitchease.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.KitchEase.Interface.UserInterface;

// @Service
// public class UserService implements UserInterface {

//     @Autowired
//     private UserRepository userRepo;

//     @Override
//     public void createUser(String userName, String firstName, String lastName, String email, String password,
//             String unit, String access) {
//        var user = new User(userName, firstName, lastName, email, password, unit, access);
//        userRepo.save(user);
//     }


   
//     public Optional<User> findById(String userId) {
//         return userRepo.findById(userId);
//     }
    
//     public Optional<User> findByUserName(String userName) {
//         return userRepo.findByUserName(userName);
//     }

//     public boolean existsById(String userId) {
//        return userRepo.existsById(userId);
//     }

//     @Override
//     public void deleteById(String userId) {
//         userRepo.deleteById(userId);
//     }

//     public Iterable<User> findAllUsers() {
//        return userRepo.findAll();
//     }






// }
package GestionKitchease.gestKitchease.service;


