package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.service.RegistrarService;
import com.src.viewgrade.Quarter;

import java.io.FileNotFoundException;
/*
    Faculty class that represents Faculty client. It implements IInstructor interface.
    It also extends from the Member class.
    Key operations are as the IInstructor interface.
*/
public class Faculty extends Member implements IInstructor {
    public Faculty(int facultyID, String cNetID) {
        super(facultyID, cNetID);
    }

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