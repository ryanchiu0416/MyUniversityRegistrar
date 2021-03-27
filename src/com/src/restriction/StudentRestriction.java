package com.src.restriction;

/*
    StudentRestriction is a data structure representing a single restriction issued to a student.
    It contains various attributes of student restrictions.

    pattern: Builder to simplify creation process.
*/
public class StudentRestriction {
    private int restrictionID;
    private String restrictionCause;
    private String dateIssued;
    private boolean hasLifted;
    private int studentID;

    private StudentRestriction(Builder builder) {
        this.restrictionID = builder.restrictionID;
        this.restrictionCause = builder.restrictionCause;
        this.dateIssued = builder.dateIssued;
        this.hasLifted = builder.hasLifted;
        this.studentID = builder.studentID;
    }

    public int getRestrictionID() {
        return restrictionID;
    }

    public String getCause() {
        return restrictionCause;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public boolean getHasLifted() {
        return hasLifted;
    }

    public int getStudentID() {
        return studentID;
    }   


    public String toString() {
        String res = "Restriction ID: " + this.restrictionID + "\n";
        res += "Cause: " + this.restrictionCause + "\n";
        res += "Issued Date: " + this.dateIssued + "\n";
        if (hasLifted) {
            res += "In effect?: NO \n\n";
        } else {
            res += "In effect?: YES \n\n";
        }
        return res;
    }

    public static class Builder {
        private int restrictionID;
        private String restrictionCause;
        private String dateIssued;
        private boolean hasLifted;
        private int studentID;

        public Builder(int restrictionID) {
            this.restrictionID = restrictionID;
        }

        public Builder addCause(String cause) {
            this.restrictionCause = cause;
            return this;
        }

        public Builder addDateIssued(String dateIssued) {
            this.dateIssued = dateIssued;
            return this;
        }

        public Builder addHasLifted(Boolean hasLifted) {
            this.hasLifted = hasLifted;
            return this;
        }

        public Builder addStudentID(int studentID) {
            this.studentID = studentID;
            return this;
        }

        public StudentRestriction build() {
            StudentRestriction sr = new StudentRestriction(this);
            return sr;
        }
    }
}
