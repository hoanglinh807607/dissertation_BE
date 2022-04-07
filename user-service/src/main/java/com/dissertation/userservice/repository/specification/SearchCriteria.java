package com.dissertation.userservice.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
