package com.nextpizza.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ValidationUtils {

    public static boolean anyNonEmpty(Object... values) {
        for (Object value : values) {
            if (value instanceof List<?> list && !list.isEmpty()) return true;
            if (value != null) return true;
        }
        return false;
    }

}
