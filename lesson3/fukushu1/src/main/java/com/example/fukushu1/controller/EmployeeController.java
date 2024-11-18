package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.model.Employee;
import com.example.fukushu1.service.CompanyService;
import com.example.fukushu1.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String showList(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("companies", companyService.findAllCompanies());
        return "employees/add-employee";
    }
    @PostMapping("/add")
    public String addEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id).orElseThrow(() -> new EntityNotFoundException("employee not found"));
        model.addAttribute("employee", employee);
        model.addAttribute("companies", companyService.findAllCompanies());
        return "employees/edit";
    }

    @PostMapping("/edit")
    public String editEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
