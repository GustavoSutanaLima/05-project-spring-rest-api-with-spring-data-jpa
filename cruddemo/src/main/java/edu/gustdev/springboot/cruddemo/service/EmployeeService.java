package edu.gustdev.springboot.cruddemo.service;

import java.util.List;

import edu.gustdev.springboot.cruddemo.entities.Employee;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee saveEmployee(Employee employeeToSave);

    void updateById(Employee employeeToUpdate, Integer id);

    void deleteById(Integer id);

}
