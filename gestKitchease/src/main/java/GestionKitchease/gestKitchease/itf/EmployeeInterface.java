package GestionKitchease.gestKitchease.itf;
import java.util.Optional;

public interface EmployeeInterface {

    void createUser(String userName, String firstName, String lastName, String email, String password, String unit, String access);
    void deleteById(String employeeId);


}
