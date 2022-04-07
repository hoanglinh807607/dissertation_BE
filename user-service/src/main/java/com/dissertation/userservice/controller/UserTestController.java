package com.dissertation.userservice.controller;

import com.dissertation.common.entities.UserTest;
import com.dissertation.userservice.VO.ResponseTemplateVO;
import com.dissertation.userservice.service.impl.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserTestController {

//    @Autowired
//    UserTestService userService;
//
//    @PostMapping("/users")
//    public UserTest saveUser(@RequestBody UserTest user) {
//        log.info("Inside saveUser of UserController");
//        return userService.saveUser(user);
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Integer userId) {
//        return userService.getUserWithDepartment(userId);
//    }
}
