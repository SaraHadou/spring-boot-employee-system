package com.springbootemployeesystem.service;

import com.springbootemployeesystem.dao.EmployeeDAO;
import com.springbootemployeesystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List< Employee > findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(Integer theId) {
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(Integer theId) {
        employeeDAO.deleteById(theId);
    }
}
