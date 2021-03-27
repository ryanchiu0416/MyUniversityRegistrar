package com.src.schedule;

import java.util.List;
/*
     ReportFormatter is a concrete instantiation of FormatStrategy interface.
     Responsible for formatting a schedule into report format.

     Pattern: Strategy (concrete class)
 */
public class ReportFormatter implements FormatStrategy {
    @Override
    public String formatSchedule(List<Course> courses) {
        StringBuilder res = new StringBuilder("(Report):\n");
        for (Course c : courses) {
            res.append(c);
        }
        return res.toString();
    }
}
