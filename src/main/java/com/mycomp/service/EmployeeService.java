package com.mycomp.service;

import com.mycomp.dto.EmployeeDTO;
import com.mycomp.entity.Employee;
import com.mycomp.exception.MyAppException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees() throws MyAppException;

    EmployeeDTO getEmployeesByName(String name) throws MyAppException;

    EmployeeDTO getEmployeesById(int id) throws MyAppException;

    String addEmployee(EmployeeDTO employeeDTO) throws MyAppException;

    String deleteEmployee(int id)throws MyAppException;

}
