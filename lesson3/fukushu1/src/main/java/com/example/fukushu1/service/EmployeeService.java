package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fukushu1.model.Employee;
import com.example.fukushu1.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    
        public Optional<Employee> findEmployeeById(Long id) {
            return employeeRepository.findById(id);
        }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
