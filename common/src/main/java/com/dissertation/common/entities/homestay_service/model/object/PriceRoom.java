package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceRoom {
    private String id;
    private Double priceInWeek;
    private Double priceWeekend;
    private String discountType;
    private Double discount;
    private Double discountAmount;
    private Double priceDiscountOneMonth;
    private Double discountAmountByMonth;
    private Boolean isActive;
    private List<DiscountByGroup> discountByGroups;
}
