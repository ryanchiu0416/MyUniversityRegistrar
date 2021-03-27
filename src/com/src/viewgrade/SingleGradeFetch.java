package com.src.viewgrade;

import com.src.db.QueryEngine;

/*
     SingleGradeFetch is a concrete instantiation of GradeFetchStrategy interface.
     Responsible for fetching the grade of a specified course and formatting.

     Pattern: Strategy (concrete class)
 */
public class SingleGradeFetch implements GradeFetchStrategy {
    private String courseID;
    private int studentID;
    public SingleGradeFetch(int studentID, String courseID) {
        this.courseID = courseID;
        this.studentID = studentID;
    }

    @Override
    public String fetchGrade() {
        // get info from QueryEngine
        String[] result = QueryEngine.getInstance().studFetchSingleGrade(studentID, courseID);
        if (result == null) {
            return "Grade for " + courseID + " cannot not found\n";
        }
        return result[0] + "   " + result[1].toUpperCase() + "   Grade: " + result[2] + "\n";
    }
}