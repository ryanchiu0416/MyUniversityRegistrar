package com.src;

import com.src.viewgrade.Quarter;
/*
    The Reference class stores constant reference data (global variable) needed for certain functions.
    Users can simply direct to this class to change constants variable value without going into various classes.
 */
public class Reference {
    private static final Quarter CURRENT_QUARTER = Quarter.winter;
    private static final int CURRENT_YEAR = 2021;
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/registrar";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "password";
    private static final String MONGODB_HOSTNAME = "localhost";
    private static final int MONGODB_PORT = 27017;
    private static final String MONGODB_DBNAME = "registrar";

    public static String getMysqlUrl() {
        return MYSQL_URL;
    }

    public static String getMysqlUsername() {
        return MYSQL_USERNAME;
    }

    public static String getMysqlPassword() {
        return MYSQL_PASSWORD;
    }

    public static String getMongodbHostname() {
        return MONGODB_HOSTNAME;
    }

    public static int getMongodbPort() {
        return MONGODB_PORT;
    }

    public static String getMongodbDbname() {
        return MONGODB_DBNAME;
    }

    public static Quarter getQuarter() {
        return CURRENT_QUARTER;
    }

    public static int getYear() {
        return CURRENT_YEAR;
    }
}
