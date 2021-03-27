package com.src.schedule;

import java.util.List;
/*
     The ScheduleFetchStrategy interface defines function(s) necessary for fetching a type of schedule.

     Pattern: Strategy (interface)
 */
public interface ScheduleFetchStrategy {
    List<Course> fetchCourses(int id);
}
