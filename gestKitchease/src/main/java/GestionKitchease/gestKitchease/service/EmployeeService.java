package GestionKitchease.gestKitchease.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GestionKitchease.gestKitchease.entity.Employee;
import GestionKitchease.gestKitchease.repository.EmployeeRepository;
import	GestionKitchease.gestKitchease.itf.EmployeeInterface;


@Service
public class EmployeeService implements EmployeeInterface {


    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public void createUser(String userName, String firstName, String lastName, String email, String password, String unit, String access) {
        var empl = new Employee(userName, firstName, lastName, email, password, unit, access);
        employeeRepo.save(empl);
    }

    @Override
    public void deleteById(String employeeId) {
        // Implementation for deleting a user by ID
    }

    public boolean existsById(Long employeeId) {
        return  employeeRepo.existsById(employeeId);
    }

    public Optional<Employee> findByUserName(String userName) {
        return employeeRepo.findByUserName(userName);
    }

    public Optional<Employee> findById(Long employeeId) {
        return employeeRepo.findById(employeeId);
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepo.findAll();
     }
    
}
