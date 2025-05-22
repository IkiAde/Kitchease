package GestionKitchease.gestKitchease.Interface;
import java.util.Optional;

public interface EmployeeInterface {

    void createUser(String userName, String firstName, String lastName, String email, String password, String status, String access);
    void deleteById(Long employeeId);


}
