package com.mkyong.jdbc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Employee {

    private Long id;
    private String name;
    private BigDecimal salary;
    private LocalDateTime createdDate;

    public Employee() {
    }

    public Employee(Long id, String name, BigDecimal salary, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.createdDate = createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", createdDate=" + createdDate +
                '}';
    }
}
