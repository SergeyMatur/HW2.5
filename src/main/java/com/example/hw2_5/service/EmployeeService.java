package com.example.hw2_5.service;

import com.example.hw2_5.exception.EmployeeAlreadyAddedException;
import com.example.hw2_5.exception.EmployeeNotFoundException;
import com.example.hw2_5.exception.EmployeeStorageIsFullException;
import com.example.hw2_5.model.Employee;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SeparatorUI;
import java.util.Objects;

@Service
public class EmployeeService {

    private final Employee[] employees = new Employee[10];

    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (Objects.equals(employees[i], employee)) {
                throw new EmployeeAlreadyAddedException();
            }
            if (Objects.isNull(employees[i])) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            employees[index] = employee;
            return employee;
        } throw new EmployeeStorageIsFullException();
    }

    public Employee findEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        for (Employee emp : employees) {
            if (Objects.equals(emp, employee)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee removeEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        for (int i = 0; i < employees.length; i++) {
            if (Objects.equals(employees[i], employee)) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException();

    }


}
