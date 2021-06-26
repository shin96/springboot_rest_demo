package com.example.department_curd.repository;

import com.example.department_curd.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepositoryI extends JpaRepository<Department, Long> {
    List<Department> findAllByDepartmentName(String name);

    List<Department> findAllByDepartmentNameIgnoreCase(String name);
}
