package com.dissertation.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum StatusOrderEnum {
    PAID,
    UNPAID,
    UNKNOWN;

    @JsonCreator
    public static StatusOrderEnum getEnumFromValue(String value) {
        return Arrays.stream(values()).filter(i -> i.toString().equals(value)).findFirst().orElse(StatusOrderEnum.UNKNOWN);
    }
}
