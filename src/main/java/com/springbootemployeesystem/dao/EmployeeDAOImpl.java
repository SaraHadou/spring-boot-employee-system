package com.springbootemployeesystem.dao;

import com.springbootemployeesystem.entity.Address;
import com.springbootemployeesystem.entity.Employee;
import com.springbootemployeesystem.entity.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List< Employee > findAll() {
        TypedQuery< Employee > theQuery = entityManager.createQuery("FROM Employee", Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(Integer theId) {
        return entityManager.find(Employee.class, theId);
    }

    @Override
    public Employee save(Employee theEmployee) {

        if (theEmployee.getId() != null) {
            Employee existingEmployee = entityManager.find(Employee.class, theEmployee.getId());

            if (existingEmployee != null) {
                existingEmployee = entityManager.merge(theEmployee);
                theEmployee = existingEmployee;
            }
        }

        // Use merge for detached or persist for new entities
        theEmployee = entityManager.merge(theEmployee);

        // Ensure cascading updates to related entities
        if (theEmployee.getAddress() != null) {
            entityManager.merge(theEmployee.getAddress());
        }

        if (theEmployee.getDepartment() != null) {
            entityManager.merge(theEmployee.getDepartment());
        }

        // Merge the Project entities
        for (Project project : theEmployee.getProjects()) {
            entityManager.merge(project);
        }
        return theEmployee;
    }

    @Override
    public void deleteById(Integer theId) {
        Employee employee = entityManager.find(Employee.class, theId);

        if (employee != null) {

            // Disassociate the employee from projects
            employee.getProjects().forEach(project -> {
                project.getEmployees().remove(employee);
            });

            // Manually delete related address
            Address address = employee.getAddress();
            if (address != null) {
                employee.setAddress(null);
                entityManager.remove(address);
            }
            entityManager.remove(employee);
        }
    }
}
