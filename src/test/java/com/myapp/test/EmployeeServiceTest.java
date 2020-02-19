package com.myapp.test;

import com.mycomp.dto.EmployeeDTO;
import com.mycomp.entity.Employee;
import com.mycomp.exception.MyAppException;
import com.mycomp.repository.EmployeeRepository;
import com.mycomp.service.EmployeeService;
import com.mycomp.service.EmployeeServiceImpl;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest extends EasyMockSupport {

    private EmployeeService fixture;
    private EmployeeRepository employeeRepository = createMock(EmployeeRepository.class);
    private List empList = new ArrayList();
    @Before
    public void setUp() {
        this.fixture = new EmployeeServiceImpl(employeeRepository);
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setAge(23);
        emp1.setDepartment("HR");
        emp1.setDescription("info..");
        emp1.setName("vikas pandya");

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setAge(24);
        emp2.setDepartment("Finance");
        emp2.setDescription("info..");
        emp2.setName("sourabh pandya");

        empList.add(emp1);
        empList.add(emp2);

    }

    @Test
    public void shouldValidateIssuerSuccessfully() throws MyAppException{
        Iterable<Employee> iterable = empList;
        EasyMock.expect(employeeRepository.findAll()).andReturn(iterable);
        EasyMock.replay(employeeRepository);

        List<EmployeeDTO> list = fixture.getAllEmployees();

        Assert.assertTrue(list.size() > 0);
    }

}
