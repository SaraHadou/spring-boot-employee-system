package com.springbootemployeesystem.service;

import com.springbootemployeesystem.entity.Employee;


import java.util.List;

public interface EmployeeService {

    List< Employee > findAll();

    Employee findById(Integer theId);

    Employee save(Employee theEmployee);

    void deleteById(Integer theId);

}
