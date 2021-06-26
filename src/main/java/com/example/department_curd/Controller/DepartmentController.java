package com.example.department_curd.Controller;

import com.example.department_curd.entity.Department;
import com.example.department_curd.exceptions.DepartmentNotFoundException;
import com.example.department_curd.service.DepartmentServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentServiceI departmentService;
    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        logger.info(department.toString());
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchAllDepartments() {
        return departmentService.fetchAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        return  departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/departments/{id}")
    public Department deleteDepartmentById(@PathVariable("id") Long id) {
        return  departmentService.deleteDepartmentById(id);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long id, @RequestBody Department department) {
        return  departmentService.updateDepartment(id, department);
    }

    @GetMapping("/departments/name/{name}")
    public List<Department> getDepartmentbyName(@PathVariable("name") String name) {
        return departmentService.getDepartmentByName(name);
    }


}
