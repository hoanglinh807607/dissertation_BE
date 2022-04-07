package com.dissertation.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum StatusHomestayEnum {
    EMPTY(0),
    ACTIVE(1),
    PENDING(2),
    UNKNOWN(3);

    private Integer value;

    private StatusHomestayEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    @JsonCreator
    public static StatusHomestayEnum getEnumFromValue(Integer value) {
        return Arrays.stream(values()).filter(i -> i.toString().equals(value)).findFirst().orElse(StatusHomestayEnum.UNKNOWN);
    }
}
