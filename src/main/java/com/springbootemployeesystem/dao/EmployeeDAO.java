package com.springbootemployeesystem.dao;

import com.springbootemployeesystem.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List< Employee> findAll();

    Employee findById(Integer theId);

    Employee save(Employee theEmployee);

    void deleteById(Integer theId);

}
