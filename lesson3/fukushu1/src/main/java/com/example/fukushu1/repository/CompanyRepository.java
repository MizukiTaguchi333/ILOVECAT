package com.example.fukushu1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}
