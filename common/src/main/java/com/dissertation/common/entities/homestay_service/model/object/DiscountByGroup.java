package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountByGroup {
    private Integer peopleNumber;
    private Double discount;
}
