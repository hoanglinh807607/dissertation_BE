package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HomestayCategoryObject {
    private String name;
    private String homestayForm;
}
