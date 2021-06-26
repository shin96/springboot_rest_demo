package com.example.department_curd.service;

import com.example.department_curd.entity.Department;
import com.example.department_curd.exceptions.DepartmentNotFoundException;
import com.example.department_curd.repository.DepartmentRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService implements DepartmentServiceI{

    @Autowired
    DepartmentRepositoryI departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        return  department.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }

    @Override
    public Department deleteDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
//        department.ifPresent(value -> departmentRepository.delete(value));
        departmentRepository.deleteById(id);
//        department.ifPresent(departmentRepository::delete);
        return department.orElse(null);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department department_from_db = departmentRepository.findById(id).orElse(null);

        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())) {
            assert department_from_db != null;
            department_from_db.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartment_code()) &&
                !"".equalsIgnoreCase(department.getDepartment_code())) {
            assert department_from_db != null;
            department_from_db.setDepartment_code(department.getDepartment_code());
        }

        if (Objects.nonNull(department.getDepartment_address()) &&
                !"".equalsIgnoreCase(department.getDepartment_address())) {
            assert department_from_db != null;
            department_from_db.setDepartment_address(department.getDepartment_address());
        }

        assert department_from_db != null;
        departmentRepository.save(department_from_db);
        return department_from_db;
    }

    @Override
    public List<Department> getDepartmentByName(String name) {
//        return departmentRepository.findAllByDepartmentName(name);
        return departmentRepository.findAllByDepartmentNameIgnoreCase(name);
    }
}
