package ru.skypro.employees.service.impl;

import ru.skypro.employees.service.EmployeeService;
import ru.skypro.employees.employee.Employee;
import ru.skypro.exception.EmployeeAlreadyAddedException;
import ru.skypro.exception.EmployeeNotFoundException;
import ru.skypro.exception.EmployeeStorageIsFullException;


import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceimpl implements EmployeeService {
    private final int maxSize = 10;

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество!");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже имеется!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден.");
        }
        employees.remove(employee);
        return employee;
    }



    @Override
    public Employee find(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employees.equals(person)) {
                return person;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден.");
    }

}
