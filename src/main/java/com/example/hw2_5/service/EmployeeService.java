package com.example.hw2_5.service;

import com.example.hw2_5.exception.EmployeeAlreadyAddedException;
import com.example.hw2_5.exception.EmployeeNotFoundException;
import com.example.hw2_5.exception.EmployeeStorageIsFullException;
import com.example.hw2_5.model.Employee;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SeparatorUI;
import java.util.*;

@Service
public class EmployeeService {

    private static int LIMIT = 10;
    private static final Map<String, Employee> employees = new HashMap<>();


    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key,employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee findEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public Employee removeEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public static List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private String getKey(String name,
                          String surname) {
        return name + "|" + surname;
    }
}
