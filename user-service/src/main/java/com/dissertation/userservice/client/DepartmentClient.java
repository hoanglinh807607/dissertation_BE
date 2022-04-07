package com.dissertation.userservice.client;

import com.dissertation.userservice.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="techefx-product-stock-service", url="localhost:8800") //Khai báo @FeignClient để biết ứng dụng khách có cách api này
@FeignClient(name = "departments-service")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    public Department findDepartmentById(@PathVariable("id") Integer id);
}
