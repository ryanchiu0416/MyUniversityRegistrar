package com.src.schedule;

import java.time.LocalTime;
/*
    Course is a data structure representing a single course.
    Implements Comparable interface.
    It contains various attributes of a Course, such as time, day, name, location, etc.

    pattern: Builder to simplifies creation process.
*/
public class Course implements Comparable<Course> {
    private static String[] daysArr = new String[] {null, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private String courseID;
    private String courseName;
    private String location;
    private LocalTime startTime; // time can be stored as LocalTime object
    private LocalTime endTime;
    private int day;
    private String dayStr;
    // public int currCapacity;
    // public int maxCapacity;
    // public String instructorName;
    
    // public int credit;
    
    private Course(Builder builder) {
        this.courseID = builder.courseID;
        this.courseName = builder.courseName;
        this.location = builder.location;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.day = builder.day;
        this.dayStr = daysArr[day];
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDay() {
        return day;
    }

    public String getDayStr() {
        return dayStr;
    }

    // This toString method is used for printing out schedule in report format
    public String toString() {
        String res = courseID + " : " + courseName + "\n";
        res += "   Location: " + location + "   |   " + startTime + "-" + endTime + ", " + daysArr[day] + "\n";
        return res;
    }

    // This abbreviation toString method is used for printing out schedule in timesheet format
    public String abbrevToString() {
        return startTime + "-" + endTime + " (" + courseID + " @ " + location + ")";
    }


    public int compareTo(Course other) {
        return this.startTime.compareTo(other.startTime);
    }


    public static class Builder {
        private String courseID;
        private String courseName;
        private String location;
        private LocalTime startTime;
        private LocalTime endTime;
        private int day;

        public Builder(String courseID) {
            this.courseID = courseID;
        }

        public Builder addName(String name) {
            this.courseName = name;
            return this;
        }

        public Builder addLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder addStartTime(LocalTime time) {
            this.startTime = time;
            return this;
        }

        public Builder addEndTime(LocalTime time) {
            this.endTime = time;
            return this;
        }

        public Builder addDay(int day) {
            this.day = day;
            return this;
        }

        public Course build() {
            Course c = new Course(this);
            return c;
        }
    }
}
