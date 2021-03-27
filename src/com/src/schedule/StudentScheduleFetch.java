package com.src.schedule;

import com.src.db.QueryEngine;

import java.util.List;
/*
     StudentScheduleFetch is a concrete instantiation of ScheduleFetchStrategy interface.
     Responsible for fetching schedule of a student.

     Pattern: Strategy (concrete class)
 */
public class StudentScheduleFetch implements ScheduleFetchStrategy {

    @Override
    public List<Course> fetchCourses(int id) {
        return QueryEngine.getInstance().studCourseCurrEnrollment(id);
    }
}
