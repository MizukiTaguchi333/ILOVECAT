package com.example.fukushu1.model;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@SQLRestriction("is_deleted = false")
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    

    public Employee(){}

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, String name, boolean isDeleted
    , Company company
    ) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.company = company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return company;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
