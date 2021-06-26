package com.example.department_curd.service;

import com.example.department_curd.entity.Department;
import com.example.department_curd.repository.DepartmentRepositoryI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentServiceI departmentService;

    @MockBean
    DepartmentRepositoryI departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("IT")
                .department_address("add -1")
                .department_code("IT-01")
                .department_id(2L)
                .build();
        List<Department> list = List.of(department);

        Mockito.when(departmentRepository.findAllByDepartmentNameIgnoreCase("IT"))
                .thenReturn(list);

    }

    @Test
    public void testForValidaDepartmentName_departmentShouldBeFound() {
        String departmentName = "IT";
        List<Department> department = departmentService.getDepartmentByName(departmentName);

        assertEquals(department.size(), 1);
        assertEquals(department.get(0).getDepartmentName(), "IT");

    }
}