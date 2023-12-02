package com.springbootemployeesystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "department",
            cascade = { CascadeType.DETACH, CascadeType.MERGE,
                        CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List< Employee > getEmployees() {
        return employees;
    }

    public void setEmployees(List< Employee > employees) {
        this.employees = employees;
    }

    public void add(Employee tempEmployee) {
        if(employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(tempEmployee);
        tempEmployee.setDepartment(this);
    }
}
