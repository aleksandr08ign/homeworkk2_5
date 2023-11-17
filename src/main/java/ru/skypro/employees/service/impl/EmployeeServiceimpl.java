package ru.skypro.employees.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employees.employee.Employee;
import ru.skypro.employees.service.EmployeeService;
import ru.skypro.exception.EmployeeAlreadyAddedException;
import ru.skypro.exception.EmployeeNotFoundException;
import ru.skypro.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceimpl implements EmployeeService {
    private final int maxSize = 3;

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество!");
        }

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже имеется!");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {

        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден.");
        }
      return employees.remove(getKey(firstName, lastName));

    }



    @Override
    public Employee find(String firstName, String lastName) {

        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден.");
            }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {

        return Collections.unmodifiableMap(employees);
    }

    private static String getKey (String firstName, String lastName) {
        return firstName + lastName;
    }
private static String getKey (Employee employee) {
        return employee.getFirstMame() + employee.getLastName();
}

}
