package GestionKitchease.gestKitchease.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import GestionKitchease.gestKitchease.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    Optional<Employee> findById(Long employeeId);
    Optional<Employee> findByUserName(String userName);
  
    boolean existsById(Long employeeId);
   
    Iterable<Employee> findAll();
    
}