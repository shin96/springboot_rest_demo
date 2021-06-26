package com.example.department_curd.service;

import com.example.department_curd.entity.Department;
import com.example.department_curd.exceptions.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentServiceI {
    Department saveDepartment(Department department);

    List<Department> fetchAllDepartments();

    Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    Department deleteDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    List<Department> getDepartmentByName(String name);
}
