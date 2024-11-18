package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.model.Employee;
import com.example.fukushu1.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }
    public Optional<Company> findCompanyById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void saveCompanyWithEmployees(Company company, List<Employee> employees) {
        company.setEmployees(employees);
        companyRepository.save(company);
    }

    public void updateCompany(Company updateCompany) {
        companyRepository.save(updateCompany);
    }

    @Transactional
    public void deleteCompany(Long companyId) {
        Company company = findCompanyById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("company not found"));
        companyRepository.delete(company);
    }
}
