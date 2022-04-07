package com.dissertation.userservice.service.impl;

import com.dissertation.common.entities.UserTest;
import com.dissertation.userservice.VO.Department;
import com.dissertation.userservice.VO.ResponseTemplateVO;
import com.dissertation.userservice.client.DepartmentClient;
import com.dissertation.userservice.repository.UserTestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserTestService {

    @Autowired
    UserTestRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentClient departmentClient;

    public UserTest saveUser(UserTest user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Integer userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        UserTest user = userRepository.findById(userId).get();
//        Department department = restTemplate.getForObject("http://departments-service/departments/" + user.getDepartmentId(), Department.class);
        vo.setUser(user);
        vo.setDepartment((Department) departmentClient.findDepartmentById(user.getDepartmentId()));
        return vo;
    }
}
