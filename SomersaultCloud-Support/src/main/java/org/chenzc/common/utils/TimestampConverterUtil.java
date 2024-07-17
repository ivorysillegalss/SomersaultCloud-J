package org.chenzc.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 整形时间戳相关转换
 * @author chenz
 * @date 2024/07/17
 */
public class TimestampConverterUtil {

    /**
     * 将时间戳转换为格式化时间字符串
     * @param timestamp
     * @return {@link String }
     *///
    public static String timestampToFormattedString(long timestamp) {
        // 将时间戳转换为 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        // 格式化日期
        return dateTime.format(formatter);
    }

    /**
     * 将格式化时间字符串转换为时间戳
     * @param formattedTime
     * @return long
     */
    public static long formattedStringToTimestamp(String formattedTime) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        // 将格式化字符串解析为 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(formattedTime, formatter);
        // 转换为时间戳
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
