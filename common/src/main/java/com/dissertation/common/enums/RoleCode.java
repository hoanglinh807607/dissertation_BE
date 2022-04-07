package com.dissertation.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

public enum RoleCode {
    SUPER_ADMIN("Super Admin"),
    ADMIN("Admin"),
    USER("User"),
    UNKNOWN("Unknown");

    private String value;

    public static List<RoleCode> ALL_ROLES = List.of(SUPER_ADMIN, ADMIN, USER);

    private RoleCode(String value) {
        this.value = value;
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }

    public static RoleCode getEnumFromValue(String value) {
        return Arrays.stream(values()).filter(i -> i.name().equals(value)).findFirst().orElse(UNKNOWN);
    }

}
