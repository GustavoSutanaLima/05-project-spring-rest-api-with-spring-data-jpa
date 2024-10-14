package edu.gustdev.springboot.cruddemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gustdev.springboot.cruddemo.entities.Employee;
import edu.gustdev.springboot.cruddemo.service.EmployeeService;
import jakarta.persistence.NoResultException;

@RestController
@RequestMapping("/rest-api")
public class EmployeeRestController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController (@Qualifier("employeeServiceImplementacao") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees(){
        List<Employee> employees = employeeService.findAll();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public String findEmployeeById(@PathVariable("id") int id){
        Employee foundEmployee = employeeService.findById(id);
        if (foundEmployee != null) {
            return foundEmployee.toString();
        }
        else {
            throw new NoResultException("Employee is not enrolled in this company.");
        }
        
    }

    @PostMapping("/employees")
    public String saveEmployee(@RequestBody Employee employeeToSave){
        employeeService.saveEmployee(employeeToSave);
        return employeeToSave.toString() + " was saved successfully.";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@RequestBody Employee employeeToUpdate, @PathVariable("id") int id){
        employeeService.updateById(employeeToUpdate, id);
        return "Employee's ID:" + id + " was updated successfully.";
    }
   
    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") int id) {
        Employee deletedEmployee = employeeService.findById(id);
        if (deletedEmployee == null) {
            throw new NoResultException("Employee is not enrolled in this company.");
        }
        employeeService.deleteById(id);
        return deletedEmployee.toString() + " was deleted.";

    }


}
