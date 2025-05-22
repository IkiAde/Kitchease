package GestionKitchease.gestKitchease.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GestionKitchease.gestKitchease.entity.Employee;
import GestionKitchease.gestKitchease.repository.EmployeeRepository;
import	GestionKitchease.gestKitchease.Interface.EmployeeInterface;


@Service
public class EmployeeService implements EmployeeInterface {


    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public void createUser(String userName, String firstName, String lastName, String email, String password, String status, String access) {
        var empl = new Employee(userName, firstName, lastName, email, password, status, access);
        employeeRepo.save(empl);
    }

    public void updateUser(Long employeeId, String userName, String firstName, String lastName, 
                      String email, String password, String status, String access) {
    
        try {
            // Find the existing employee???
            Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
            
            // Update the employee fields
            employee.setUserName(userName);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setStatus(status);
            employee.setAccess(access);
            
            // Only update password if it's changed  and not empty
            if (password != null && !password.trim().isEmpty()) {
              
                employee.setPassword(password);  // Use this if passwords are stored as plain text
            }
            
            // Save the updated employee
            employeeRepo.save(employee);
            
            System.out.println("Employee updated successfully: " + employeeId);
            
        } catch (Exception e) {
            System.err.println("Error in updateUser service method: " + e.getMessage());
            throw e; 
        }
    }

    public void deleteById(Long employeeId) {
        employeeRepo.deleteById(employeeId);
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

    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
     }
    
}
