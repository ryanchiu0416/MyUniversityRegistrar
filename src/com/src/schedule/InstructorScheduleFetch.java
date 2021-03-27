package com.src.schedule;

import com.src.db.QueryEngine;
import java.util.List;
/*
     InstructorScheduleFetch is a concrete instantiation of ScheduleFetchStrategy interface.
     Responsible for fetching schedule of an instructor.

     Pattern: Strategy (concrete class)
 */
public class InstructorScheduleFetch implements ScheduleFetchStrategy {

    @Override
    public List<Course> fetchCourses(int id) {
        return QueryEngine.getInstance().instrCourseTeachingSchedule(id);
    }
    
}
