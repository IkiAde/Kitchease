package GestionKitchease.gestKitchease.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Employee {
    

    @Id
    @GeneratedValue
    private Long employeeId;

    private String userName;
   
    private String firstName;
    private String lastName;
    
    private String email;
    private String password;

    private String status;
    private String access;

    public Employee() {
    }


    public Employee(String userName, String firstName, String lastName, String email, String password,
        String unit, String access) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = unit;
        this.access = access;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
  
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String unit) {
        this.status = unit;
    }
    public String getAccess() {
        return access;
    }
    public void setAccess(String access) {
        this.access = access;
    }




}
