package com.src.transcript;

/*
    StudentProfile is a data structure representing a profile belonging to a single student.
    It contains basic information about a student, such as major, name, etc.

    pattern: Builder to simplify creation process.
*/
public class StudentProfile {
    private String name;
    private int studentID;
    private String cNetID;
    private String major;
    private String department;
    private String email;
    // public Image photo;
    private StudentProfile(Builder builder) {
        this.studentID = builder.studentID;
        this.cNetID = builder.cNetID;
        this.major = builder.major;
        this.department = builder.department;
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getName() {
        return this.name;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getMajor() {
        return this.major;
    }

    public String getCNetID() {
        return this.cNetID;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getEmail() {
        return this.email;
    }

    public String toString() {
        String result = "Name: " + name + "\n";
        result += "Student ID: " + studentID + "\n";
        result += "CNetID: " + cNetID + "\n";
        result += "Major: " + major + "\n";
        result += "Department: " + department + "\n";
        return result;
    }


    public static class Builder {
        private String name;
        private int studentID;
        private String cNetID;
        private String major;
        private String department;
        private String email;

        public Builder(int studentID) {
            this.studentID = studentID;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addCNetID(String cNetID) {
            this.cNetID = cNetID;
            return this;
        }

        public Builder addMajor(String major) {
            this.major = major;
            return this;
        }

        public Builder addDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentProfile build() {
            StudentProfile p = new StudentProfile(this);
            return p;
        }
    }
}
