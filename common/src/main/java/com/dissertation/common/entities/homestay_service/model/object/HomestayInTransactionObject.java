package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayInTransactionObject {
    private String homestayId;
    private String homestayName;
    private Double priceBooking;
}
