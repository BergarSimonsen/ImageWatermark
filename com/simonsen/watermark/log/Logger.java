package com.simonsen.watermark.log;

import java.util.*;

/*************************************
 * Simple logger class for logging
 * various messages and errors.
 *
 * @author Bergar Simonsen
 * @since Thu Jul 19 23:03:19 +00 2018
 *************************************/

public class Logger {

    public static final String INFO_PREFIX = "I";    
    public static final String DEBUG_PREFIX = "D";
    public static final String WARNING_PREFIX = "W";
    public static final String ERROR_PREFIX = "E";

    public static void logInfo(String message) {
	log(INFO_PREFIX, message);
    }

    public static void logDebug(String message) {
	log(DEBUG_PREFIX, message);
    }

    public static void logWarning(String message) {
	log(WARNING_PREFIX, message);
    }

    public static void logError(String message) {
	log(ERROR_PREFIX, message);
    }

    private static void log(String prefix, String message) {
	System.out.printf("%s :: %s%n", prefix, message);
    }
}
