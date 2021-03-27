package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.service.RegistrarService;
import com.src.viewgrade.Quarter;

/*
    Student class represents student clients. It implements the IStudent interface.
    It also extends from the Member class.
    For key capabilities, refer to documentation of IStudent.
*/
public class Student extends Member implements IStudent {
    public Student(int studentID, String cNetID) {
        super(studentID, cNetID);
    }

    @Override
    public String studViewGrade(String courseID) {
        //single grade
        return RegistrarService.getInstance().getGrades(this.idNumber, courseID);
    }

    @Override
    public String studViewQuarterlyGrades(Quarter q, int year) {
        //quarterly grade
        return RegistrarService.getInstance().getGrades(this.idNumber, q, year);
    }

    @Override
    public String studViewAllGrades() {
        //all grades
        return RegistrarService.getInstance().getGrades(this.idNumber);
    }

    @Override
    public String studViewRestrictionStatus() {
        return RegistrarService.getInstance().getRestrictions(this.idNumber);
    }

    @Override
    public String studViewStudentSchedule(FormatStrategy formatter) {
        return RegistrarService.getInstance().getClassSchedule(this.idNumber, formatter);
    }

    @Override
    public String studViewOfficialTranscript() {
        return RegistrarService.getInstance().getTranscript(this.idNumber);
    }
}
