package com.example.department_curd.repository;

import com.example.department_curd.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class DepartmentRepositoryITest {

    @Autowired
    DepartmentRepositoryI departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .department_address("address - 1")
                .department_code("IT-01")
                .build();

        testEntityManager.persist(department);

    }

    @Test
    void testFindById() {
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "IT");
    }
}