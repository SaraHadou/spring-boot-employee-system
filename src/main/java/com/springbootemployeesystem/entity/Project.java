package com.springbootemployeesystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @JsonBackReference
    @ManyToMany(mappedBy = "projects", cascade = CascadeType.REMOVE)
    private List<Employee> employees;


    public Project() {
    }

    public Project(String name, int duration) {
        this.name = name;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List< Employee > getEmployees() {
        return employees;
    }

    public void setEmployees(List< Employee > employees) {
        this.employees = employees;
    }
}
