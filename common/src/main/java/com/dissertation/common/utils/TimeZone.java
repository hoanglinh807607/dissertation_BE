package com.dissertation.common.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TimeZone {
    UTC_SUB_12("UTC-12", "-12:00"),
    UTC_SUB_11("UTC-11", "-11:00"),
    UTC_SUB_10("UTC-10", "-10:00"),
    UTC_SUB_9_30("UTC-9:30", "-09:30"),
    UTC_SUB_9("UTC-09", "-09:00"),
    UTC_SUB_8("UTC-08", "-08:00"),
    UTC_SUB_7("UTC-07", "-07:00"),
    UTC_SUB_6("UTC-06", "-06:00"),
    UTC_SUB_5("UTC-05", "-05:00"),
    UTC_SUB_4("UTC-04", "-04:00"),
    UTC_SUB_3("UTC-03", "-03:00"),
    UTC_SUB_2_30("UTC-2:30", "-02:30"),
    UTC_SUB_2("UTC-2", "-02:00"),
    UTC_SUB_1("UTC-1", "-01:00"),
    UTC("UTC+0", "+00:00"),
    UTC_ADD_1("UTC+1", "+01:00"),
    UTC_ADD_2("UTC+2", "+02:00"),
    UTC_ADD_3("UTC+3", "+03:00"),
    UTC_ADD_4("UTC+4", "+04:00"),
    UTC_ADD_4_30("UTC+4:30", "+04:30"),
    UTC_ADD_5("UTC+5", "+05:00"),
    UTC_ADD_5_30("UTC+5:30", "+05:30"),
    UTC_ADD_5_45("UTC+5:45", "+05:45"),
    UTC_ADD_6("UTC+6", "+06:00"),
    UTC_ADD_6_30("UTC+6:30", "+06:30"),
    UTC_ADD_7("UTC+7", "+07:00"),
    UTC_ADD_8("UTC+8", "+08:00"),
    UTC_ADD_8_45("UTC+8:45", "+08:45"),
    UTC_ADD_9("UTC+9", "+09:00"),
    UTC_ADD_9_30("UTC+9:30", "+09:30"),
    UTC_ADD_10("UTC+10", "+10:00"),
    UTC_ADD_10_30("UTC+10:30", "+10:30"),
    UTC_ADD_11("UTC+11", "+11:00"),
    UTC_ADD_12("UTC+12", "+12:00"),
    UTC_ADD_12_45("UTC+12:45", "+12:45"),
    UTC_ADD_13("UTC+13", "+13:00"),
    UTC_ADD_14("UTC+14", "+14:00"),
    UNKNOWN("", "");

    private String code;
    private String zoneId;
    private TimeZone(String code, String zoneId) {
        this.code = code;
        this.zoneId = zoneId;
    }

    @JsonCreator
    public static TimeZone getEnumFromValue(String value) {
        return Arrays.stream(values()).filter(i -> i.getCode().equals(value)).findFirst().orElse(TimeZone.UNKNOWN);
    }
}