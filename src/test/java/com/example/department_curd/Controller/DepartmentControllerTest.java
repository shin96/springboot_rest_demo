package com.example.department_curd.Controller;

import com.example.department_curd.entity.Department;
import com.example.department_curd.exceptions.DepartmentNotFoundException;
import com.example.department_curd.service.DepartmentServiceI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentServiceI departmentService;

    Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("ME")
                .department_id(1L)
                .department_address("addr - 2")
                .department_code("ME-1")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("ME")
                .department_address("addr - 2")
                .department_code("ME-1")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "departmentName": "ME",
                            "department_address": "addr - 2",
                            "department_code": "ME-1"
                        }"""))
                .andExpect(status().isOk());

    }

    @Test
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/api/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department
                                .getDepartmentName()));
    }
}