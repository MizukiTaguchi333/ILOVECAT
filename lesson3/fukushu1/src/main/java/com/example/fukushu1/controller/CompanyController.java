package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.service.CompanyService;
import com.example.fukushu1.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String companyList(Model model) {
        List<Company> companies = companyService.findAllCompanies();
        model.addAttribute("companies", companies);
        return "companies/list";
    }

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("employees", employeeService.findAllEmployees());
        return "companies/add-company";
    }

    @PostMapping("/add")
    public String addCompany(Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/edit/{id}")
    public String showEditCompanyForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.findCompanyById(id)
            .orElseThrow(() -> new EntityNotFoundException("company not found"));
        model.addAttribute("company", company);
        model.addAttribute("allEmployees", employeeService.findAllEmployees());
        return "companies/edit";
    }

    @PostMapping("/edit")
    public String updateCompany(Company company) {
            companyService.updateCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
}
