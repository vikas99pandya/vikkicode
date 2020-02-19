package com.mycomp.service;

import com.mycomp.dto.EmployeeDTO;
import com.mycomp.entity.Employee;
import com.mycomp.exception.MyAppException;
import com.mycomp.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws MyAppException {
        List<EmployeeDTO> employeeList = new ArrayList<>();

        Iterable<Employee> iterable = employeeRepository.findAll();

        for (Employee employee : iterable) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            employeeList.add(employeeDTO);
        }

        return employeeList;
    }

    @Override
    public EmployeeDTO getEmployeesByName(String name) throws MyAppException{
        Optional<Employee> employee = employeeRepository.findByName(name);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee.get(), employeeDTO);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeesById(int id) throws MyAppException{
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee.get(), employeeDTO);
        return employeeDTO;
    }

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) throws MyAppException{
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeRepository.save(employee);
        return "success";
    }

    @Override
    public String deleteEmployee(int id) throws MyAppException{
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return "success";
        }
        else{
            return "no employee present with given id: "+id;
        }
    }
}
