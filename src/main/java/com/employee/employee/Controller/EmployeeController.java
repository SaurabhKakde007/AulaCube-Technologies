package com.employee.employee.Controller;

import com.employee.employee.Payload.EmployeeDto;
import com.employee.employee.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/employees

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/employees
    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //http://localhost:8080/api/employees/1
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable("id") long id
    ){
        EmployeeDto dto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/employees/1
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestBody EmployeeDto employeeDto ,
            @PathVariable("id") long id
    ){
        EmployeeDto dto1 = employeeService.updateEmployee(employeeDto, id);

        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }

    //http://localhost:8080/api/employees/4
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee entity deleted",HttpStatus.OK);
    }
}
