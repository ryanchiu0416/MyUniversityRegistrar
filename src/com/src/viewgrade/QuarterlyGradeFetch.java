package com.src.viewgrade;

import com.src.db.QueryEngine;

import java.util.*;

/*
     QuarterlyGradeFetch is a concrete instantiation of GradeFetchStrategy interface.
     Responsible for fetching the grades of a specified academic quarter and formatting.

     Pattern: Strategy (concrete class)
 */
public class QuarterlyGradeFetch implements GradeFetchStrategy {
    private int studentID;
    private Quarter quarter;
    private int academicYear;
    public QuarterlyGradeFetch(int studentID, Quarter quarter, int academicYear) {
        this.studentID = studentID;
        this.quarter = quarter;
        this.academicYear = academicYear;
    }

    @Override
    public String fetchGrade() {
        // get info from QueryEngine
        List<String[]> items = QueryEngine.getInstance().studFetchQuarterlyGrades(studentID, quarter, academicYear);
        if (items.size() == 0) {
            return "No course grade in " + quarter.toString().toUpperCase() + " " + academicYear + "\n";
        }
        StringBuilder res = new StringBuilder();
        res.append("*********************************************\n");
        res.append(quarter.toString().toUpperCase()).append(" ").append(academicYear).append("\n");
        for (String[] item : items) {
            res.append("   ").append(item[0]).append("   Grade: ").append(item[1]).append("\n");
        }
        res.append("*********************************************\n");
        return res.toString();
    }
}