package ru.skypro.employees.service;

import ru.skypro.employees.employee.Employee;
public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);

}
