package com.musicabinet.mobile.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Kirchhoff-
 */

public class DateUtils {

    // ISO 8601 constants
    private static final String ISO_8601_PATTERN_1 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String ISO_8601_PATTERN_2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final SimpleDateFormat RESULT_PATTERN = new SimpleDateFormat("dd MMMM");
    private static final String[] SUPPORTED_ISO_8601_PATTERNS = new String[]{ISO_8601_PATTERN_1,
            ISO_8601_PATTERN_2};
    private static final int TICK_MARK_COUNT = 2;
    private static final int COLON_PREFIX_COUNT = "+00".length();

    private DateUtils() {
        // hide constructor
    }

    /**
     * Parses a date from the specified ISO 8601-compliant string.
     *
     * @param string the string to parse
     * @return the {@link Date} resulting from the parsing, or null if the string could not be
     * parsed
     */
    public static String parseIso8601DateTime(String string) {
        if (string == null) {
            return null;
        }
        String s = string.replace("Z", "+00:00");
        for (String pattern : SUPPORTED_ISO_8601_PATTERNS) {
            String str = s;
            int colonPosition = pattern.lastIndexOf('Z') - TICK_MARK_COUNT + COLON_PREFIX_COUNT;
            if (str.length() > colonPosition) {
                str = str.substring(0, colonPosition) + str.substring(colonPosition + 1);
            }
            try {

                Date dateFromServer = new SimpleDateFormat(pattern, Locale.US).parse(str);
                return RESULT_PATTERN.format(dateFromServer);
            } catch (final ParseException e) {
                // try the next one
            }
        }
        return null;
    }
}
