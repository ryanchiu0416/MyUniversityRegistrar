package com.src.schedule;

import com.src.Reference;

import java.util.List;
/*
    ScheduleEngine class handles tasks related to schedule generation.
    Main operation(s): generate course schedule for a specific member.

    Pattern used: Singleton
*/
public class ScheduleEngine {
    private static ScheduleEngine instance = null;
    public static ScheduleEngine getInstance() {
        if (instance == null) {
            instance = new ScheduleEngine();
        }
        return instance;
    }
    private ScheduleEngine() {}

    public String generateSchedule(int id, ScheduleFetchStrategy fetchStrategy, FormatStrategy formatter) {
        // fetch data
        List<Course> courseList = fetchStrategy.fetchCourses(id);
        if (courseList.isEmpty()) {
            return "No course planned in schedule";
        }
        return Reference.getQuarter().toString().toUpperCase() + " " + Reference.getYear() + " "
                + formatter.formatSchedule(courseList);
    }
}
