package com.dissertation.userservice.VO;

import com.dissertation.common.entities.UserTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private UserTest user;
    private Department department;
}
