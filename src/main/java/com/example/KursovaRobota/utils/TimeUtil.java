package com.example.KursovaRobota.utils;

import java.sql.Time;

public class TimeUtil {
    public static Time correctTime(String str) {
        String[] strings = str.split(":");
        if (strings.length == 2) {
            str += ":00";
        }
        return Time.valueOf(str);
    }
}
