package com.src.roster;

import com.src.transcript.StudentProfile;
import java.util.List;

/*
    Roster class represents data structure of a single Roster of a specific class.

    pattern: Builder to simplify creation process.
*/
public class Roster {
    private String courseID;
    private List<StudentProfile> studentList;

    private Roster(Builder builder) {
        this.courseID = builder.courseID;
        this.studentList = builder.studentList;
    }

    public String getCourseID() {
        return courseID;
    }

    public List<StudentProfile> getStudentList() {
        return studentList;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Roster for course : " + courseID + "\n");
        result.append(studentList.size()).append(" Students:\n");
        for (StudentProfile stud : studentList) {
            result.append("  ").append(stud.getName()).append(", studentID: ").append(stud.getStudentID())
                    .append(", cNetID: ").append(stud.getCNetID()).append(", Major: ").append(stud.getMajor())
                    .append("\n");
        }
        return result.toString();
    }

    public static class Builder {
        private String courseID;
        private List<StudentProfile> studentList;
        public Builder(String courseID) {
            this.courseID = courseID;
        }

        public Builder addStudents(List<StudentProfile> studentList) {
            this.studentList = studentList;
            return this;
        }

        public Roster build() {
            return new Roster(this);
        }
    }
}