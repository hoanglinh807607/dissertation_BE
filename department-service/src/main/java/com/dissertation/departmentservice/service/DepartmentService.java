package com.dissertation.departmentservice.service;

import com.dissertation.departmentservice.entity.DepartmentEntity;
import com.dissertation.departmentservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentEntity createDeparment(DepartmentEntity departmentEntity) {
        log.info("inside createDeparment of DepartmentService");
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity findDepartmentById(Integer id) {
        log.info("inside findDepartmentById of DepartmentService");
        return departmentRepository.findById(id).get();
    }
}
