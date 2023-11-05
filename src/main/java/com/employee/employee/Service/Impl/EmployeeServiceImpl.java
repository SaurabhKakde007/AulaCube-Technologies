package com.employee.employee.Service.Impl;

import com.employee.employee.Entity.Employee;
import com.employee.employee.Exception.ResourceNotFoundException;
import com.employee.employee.Payload.EmployeeDto;
import com.employee.employee.Repository.EmployeeRepository;
import com.employee.employee.Service.EmployeeService;
import org.springframework.stereotype.Service;
import java.security.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = mapToEntity(employeeDto);

        Employee newEmployee = employeeRepository.save(employee);

        EmployeeDto x = mapToDTO(newEmployee);
        return x;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        return all.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        employee.setCreatedAt(employeeDto.getCreatedAt());
        employee.setUpdatedAt(employeeDto.getUpdatedAt());

        Employee update = employeeRepository.save(employee);

        return mapToDTO(update);

    }

    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);

    }

    private EmployeeDto mapToDTO(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartmentId(employee.getDepartmentId());
        employeeDto.setCreatedAt(employee.getCreatedAt());
        employeeDto.setUpdatedAt(employee.getUpdatedAt());
        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        employee.setCreatedAt(employeeDto.getCreatedAt());
        employee.setUpdatedAt(employeeDto.getUpdatedAt());
        return employee;
    }
}
