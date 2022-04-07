package com.dissertation.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_DATE_SLASH = "yyyy/MM/dd";

    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATE_APPROVAL = "yyyyMMddHHmmss";

    public static final String ISO_FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String FORMAT_DATE_TIME_DELETE = "yyyyMMdd_HHmmss";

    public static final String FORMAR_DATE_CSV = "MMM d, y HH:mm";

    public static final String ISO_8601_FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static Date parseDateOrNull(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
            return dateFormat.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDateOrNull(String dateTime, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateTime);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDateOrNow(String dateTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
            return dateFormat.parse(dateTime);
        } catch (Exception ex) {
            return new Date();
        }
    }

    public static Date parseDateOrNowForRange(String dateTime, boolean isFrom) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
            if (isFrom) {
                return dateFormat.parse(dateTime + " 00:00:00");
            }
            return dateFormat.parse(dateTime + " 23:59:59");
        } catch (Exception ex) {
            return new Date();
        }
    }

    public static String parseStringDateTimeOrNull(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringDateTimeOrNull(Date date, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringDateOrNull(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringDateOrNullCsv(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAR_DATE_CSV);
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringDateOrEmpty(LocalDate localDate) {
        try {
            return localDate.toString();
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static String parseStringDateOrEmpty(LocalDate localDate, String datePattern) {
        try {
            return localDate.format(DateTimeFormatter.ofPattern(datePattern));
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static String parseStringDateOrEmpty(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(FORMAT_DATE_TIME));
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static LocalDate parseLocalDateOrNull(Date date) {
        try {
            return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDate parseLocalDateOrNull(Calendar date) {
        try {
            return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringOrEmpty(LocalDate date) {
        try {
            return date.toString();
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static String parseStringOrEmpty(LocalDateTime date) {
        try {
            return date.format(DateTimeFormatter.ofPattern(FORMAT_DATE));
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public static LocalDate parseLocalDateOrNull(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDate parseLocalDateOrNull(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDate parseLocalDateOrNullPaddingZero(String value, String format) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }
        String date = Arrays.asList(value.split("/")).stream().skip(0).map(str -> StringUtils.leftPad(str, 2, '0'))
                .collect(Collectors.joining("/"));
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTimeOrNull(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTimeOrNull(Date date) {
        try {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDateOrNull(LocalDate localDate) {
        try {
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDateOrNull(LocalDateTime localDateTime) {
        try {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDate parseLocalDateOrNull(Long date) {
        try {
            return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTimeOrNull(Long date) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean checkFormatDate(String dateStr, String dateFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            Date date = formatter.parse(dateStr);

            return formatter.format(date).equalsIgnoreCase(dateStr);
        } catch (Exception e) {
            return false;
        }
    }

    public static Long parseLongApprovalOrNull(String dateTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_APPROVAL);
            return dateFormat.parse(dateTime).getTime();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseStringLongApprovalOrNull(Long date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_APPROVAL);
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTimeOrNull(LocalDate localDate) {
        try {
            return localDate.atStartOfDay();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat(FORMAT_DATE_TIME_DELETE).format(new Date(System.currentTimeMillis()));
    }

    /**
     * Convert local time with time zone to UTC
     * @param time format hh:mm or hh:mm:ss
     * @param timeZone time zone id
     *      <pre>Example for timeZone:</pre>
     *          <pre>. <strong>UTC_ADD_7</strong> This is equivalent +07:00</pre>
     *
     * @return LocalTime with format <strong>hh:mm</strong>
     */
    public static LocalTime convertLocalToUTC(String time, TimeZone timeZone) {
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(time);
        } catch (Exception e) {
            throw new IllegalArgumentException("Time not match format.");
        }
        if (timeZone == TimeZone.UNKNOWN) {
            throw new IllegalArgumentException("ZoneId not match format.");
        }
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);
        ZonedDateTime zoneDateTime = localDateTime.atZone(ZoneId.of(timeZone.getZoneId()));
        return zoneDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalTime();
    }

    /**
     * Convert UTC to local time with time zone
     * @param time format hh:mm or hh:mm:ss
     * @param timeZone time zone id
     *      <pre>Example for timeZone:</pre>
     *          <pre>. <strong>UTC_ADD_7</strong> This is equivalent +07:00</pre>
     *
     * @return LocalTime of the Time Zone
     */
    public static LocalTime convertUTCToLocal(LocalTime time, TimeZone timeZone) {
        if (timeZone == TimeZone.UNKNOWN) {
            throw new IllegalArgumentException("ZoneId not match format.");
        }
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), time);
        ZonedDateTime zoneDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return zoneDateTime.withZoneSameInstant(ZoneId.of(timeZone.getZoneId())).toLocalTime();
    }

    /**
     * Convert UTC to ZonedDateTime with time zone
     * @param time format date
     * @param timeZone time zone id
     *      <pre>Example for timeZone:</pre>
     *          <pre>. <strong>Asia/Bangkok</strong> This is equivalent zone area: Asia Bangkok</pre>
     *
     * @return Date of the Time Zone
     */
    public static Date convertUTCToLocal(Date date, String timeZone) {
        try {
            SimpleDateFormat localFormatter = new SimpleDateFormat(ISO_FORMAT_DATE_TIME);
            java.util.TimeZone localTimeZone = java.util.TimeZone.getTimeZone(timeZone);
            localFormatter.setTimeZone(localTimeZone);
            return new SimpleDateFormat(ISO_FORMAT_DATE_TIME).parse(localFormatter.format(date));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * UTC now ISO-8601 format
     * @return ZonedDateTime
     */
    public static ZonedDateTime now() {
        DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);
        return ZonedDateTime.parse(DateTimeFormatter.ISO_INSTANT.format(Instant.now()), f);
    }

    /**
     * Parse Date to UTC ISO-8601 format
     *
     * @return ZonedDateTime
     */
    public static ZonedDateTime parse(Date date) {
        DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);
        return ZonedDateTime
                .parse(DateTimeFormatter.ISO_INSTANT.format(Instant.parse(parseStringDateTimeOrNull(date, ISO_8601_FORMAT_DATE_TIME))), f);
    }
}