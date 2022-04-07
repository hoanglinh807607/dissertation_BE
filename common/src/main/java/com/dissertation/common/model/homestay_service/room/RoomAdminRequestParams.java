package com.dissertation.common.model.homestay_service.room;

import lombok.Data;

@Data
public class RoomAdminRequestParams {
    private String timeZone;
    private String keyword;
    private String status;
    private String homestayId;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String from;
    private String to;
    private String sortBy = "createAt";
    private String direction = "DESC";
}
