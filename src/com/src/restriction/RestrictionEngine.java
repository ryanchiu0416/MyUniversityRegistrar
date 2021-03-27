package com.src.restriction;

import com.src.db.QueryEngine;
import java.util.List;
/*
    RestrictionEngine class handles tasks related to student restrictions.
    Main operation(s): generate summary on restriction(s) for a specific student.

    Pattern used: Singleton
*/
public class RestrictionEngine {
    private static RestrictionEngine instance = null;
    public static RestrictionEngine getInstance() {
        if (instance == null) {
            instance = new RestrictionEngine();
        }
        return instance;
    }
    private RestrictionEngine() {}


    public String generateRestrictionSummary(int studentID) {
        List<StudentRestriction> restrictionList = QueryEngine.getInstance().studFetchRestrictions(studentID);
        String result = "******Restriction Summary for studentID = " + studentID + "******\n";
        if (restrictionList.size() == 0) {
            result += "**********Student ID = " + studentID + " CAN REGISTER**********\n";
            return result;
        }

        boolean canRegister = true;
        for (StudentRestriction r : restrictionList) {
            result += r;
            if (!r.getHasLifted()) { // not lifted means cannot register
                canRegister = false;
            }
        }
        result = result.substring(0, result.length()-1);
        result += "**************************************************\n";
        if (canRegister) {
            result += "**********Student ID = " + studentID + " CAN REGISTER**********\n";
        } else {
            result += "**********Student ID = " + studentID + " CANNOT REGISTER**********\n";
        }
        result += "**************************************************\n";
        return result;
    }
}
