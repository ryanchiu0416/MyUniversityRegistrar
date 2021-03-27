package com.src.schedule;

import java.util.List;
/*
     The FormatStrategy interface defines function(s) necessary for formatting of course/teaching
     schedules.

     Pattern: Strategy (interface)
 */
public interface FormatStrategy {
    String formatSchedule(List<Course> courses);
}
