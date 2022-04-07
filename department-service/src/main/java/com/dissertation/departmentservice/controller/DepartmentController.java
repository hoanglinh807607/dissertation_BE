package com.dissertation.departmentservice.controller;

import com.dissertation.departmentservice.entity.DepartmentEntity;
import com.dissertation.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity departmentEntity) {
        log.info("inside saveDepartment method of DepartmentController");
        return departmentService.createDeparment(departmentEntity);
    }

    @GetMapping("/{id}")
    public DepartmentEntity findDepartmentById(@PathVariable("id") Integer id) {
        log.info("inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(id);
    }
}
