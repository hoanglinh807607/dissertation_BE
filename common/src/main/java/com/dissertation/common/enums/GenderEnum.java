package com.dissertation.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum GenderEnum {
    MALE("Male", 0),
    FEMALE("Female", 1),
    UNKNOWN("Unknown", -1);

    private String valueInJson;
    private int value;

    private GenderEnum(String valueInJson, int value) {
        this.valueInJson = valueInJson;
        this.value = value;
    }

    @JsonValue
    public String toValueString() {
        return valueInJson;
    }

    public int toValue() {
        return value;
    }

    @JsonCreator
    public static GenderEnum getEnumFromValue(String value) {
        return Arrays.stream(values()).filter(i -> i.valueInJson.equals(value)).findFirst().orElse(UNKNOWN);
    }
}
