package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.service.RegistrarService;
import com.src.viewgrade.Quarter;

import java.io.FileNotFoundException;

/*
    StudentTA class represents a Teaching Assistant who is also a student; it implements both IStudent and IInstructor
    interfaces. It also extends from the Member class.
    For key operations available for the class, refer to the two interfaces mentioned.
 */
public class StudentTA extends Member implements IStudent, IInstructor {
    public StudentTA(int id, String cNetID) {
        super(id, cNetID);
    }

    /* Student relevant methods */
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



    /* Instructor relevant methods */

    @Override
    public String instrViewTeachingSchedule(FormatStrategy formatter) {
        return RegistrarService.getInstance().getTeachingSchedule(this.idNumber, formatter);
    }

    @Override
    public String instrGetClassRoster(String courseID) {
        return RegistrarService.getInstance().getRoster(this.idNumber, courseID);
    }

    @Override
    public String instrSendClassEmail(String courseID, String message) {
        return RegistrarService.getInstance().sendClassEmail(this.idNumber, courseID, message);
    }

    @Override
    public String instrGetGradeSheet(String courseID, Quarter q, int year) throws FileNotFoundException {
        return RegistrarService.getInstance().generateGradeSheet(this.idNumber, courseID, q, year);
    }

    @Override
    public String instrUploadGradeSheet(String courseID, Quarter q, int year, String filePath) throws FileNotFoundException {
        return RegistrarService.getInstance().updateGrades(this.idNumber, courseID, filePath, q, year);
    }

    @Override
    public String instrGetAllCourseTaught() {
        return RegistrarService.getInstance().getAllCourseTaught(this.idNumber);
    }
}
