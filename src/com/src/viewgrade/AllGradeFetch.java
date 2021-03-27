package com.src.viewgrade;

import com.src.db.QueryEngine;

import java.util.*;

/*
     AllGradeFetch is a concrete instantiation of GradeFetchStrategy interface.
     Responsible for fetching all grades on record and formatting.

     Pattern: Strategy (concrete class)
 */
public class AllGradeFetch implements GradeFetchStrategy {
    private int studentID;
    public AllGradeFetch(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String fetchGrade() {
        // get info from QueryEngine
        StringBuilder res = new StringBuilder();
        Map<String, List<String[]>> grades = QueryEngine.getInstance().studFetchAllGrades(studentID);
        res.append("*********************************************\n");
        for (String key : grades.keySet()) {
            res.append(key).append("\n");
            for (String[] grade : grades.get(key)) {
                res.append("   ").append(grade[0]).append("   Grade: ").append(grade[1]).append("\n");
            }
            res.append("*********************************************\n");
        }
        return res.toString();
    }
}