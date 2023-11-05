package com.employee.employee.Service;

import com.employee.employee.Payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

   EmployeeDto createEmployee(EmployeeDto employeeDto);

   List<EmployeeDto> getAllEmployees();

   EmployeeDto getEmployeeById(long id);

   EmployeeDto updateEmployee(EmployeeDto employeeDto, long id);

   void deleteEmployeeById(long id);
}
