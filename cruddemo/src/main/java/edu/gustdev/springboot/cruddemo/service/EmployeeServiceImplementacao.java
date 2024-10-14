package edu.gustdev.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gustdev.springboot.cruddemo.dao.EmployeeRepository;
import edu.gustdev.springboot.cruddemo.entities.Employee;

@Service
public class EmployeeServiceImplementacao implements EmployeeService {

    EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeServiceImplementacao(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        if (employeeRepo.findById(id).isPresent()) {
            return employeeRepo.findById(id).get();
        }
        else {
            throw new RuntimeException("Não foi possível encontrar um employee com o id: " + id);
        }
            
    }

    @Override
    public void updateById(Employee employeeToUpdate, Integer id) {
        Employee employeeDB = findById(id);
        employeeDB.setFirstName(employeeToUpdate.getFirstName());
        employeeDB.setLastName(employeeToUpdate.getLastName());
        employeeDB.setEmail(employeeToUpdate.getEmail());
        employeeRepo.save(employeeDB);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee saveEmployee(Employee employeeToSave) {
        return employeeRepo.save(employeeToSave);
    }

    

}
