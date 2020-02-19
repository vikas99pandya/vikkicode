package com.mycomp.controller;

import com.mycomp.dto.EmployeeDTO;
import com.mycomp.exception.MyAppException;
import com.mycomp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/mydata")
    public ResponseEntity<String> addEmpolyee(@RequestBody @Valid EmployeeDTO employeeDTO) throws MyAppException {
        String result=employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/mydata/{id}")
    public ResponseEntity<String> removeEmpolyee(@PathVariable("id") int id) throws MyAppException{
        String result=employeeService.deleteEmployee(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/mydata")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(@RequestHeader Map<String, String> headers) throws MyAppException{
        //header.get("Authorization");
        List<EmployeeDTO> listEmployee = employeeService.getAllEmployees();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    @GetMapping("/mydata/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") int id) throws MyAppException{
        EmployeeDTO employee = employeeService.getEmployeesById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


}


