package com.employee.employee.Payload;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
@Data
public class EmployeeDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
