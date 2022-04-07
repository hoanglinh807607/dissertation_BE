package com.dissertation.common.model.homestay_service.price_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRoomRequestParams {
    private String keyword;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "createAt";
    private String direction = "DESC";
}
