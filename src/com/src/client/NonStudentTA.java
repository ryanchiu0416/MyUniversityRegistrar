package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.service.RegistrarService;
import com.src.viewgrade.Quarter;

import java.io.FileNotFoundException;
/*
    NonStudentTA class represents a Teaching Assistant who is not a student; it implements IInstructor
    interface. It also extends from the Member class.
    For key operations available for the class, refer to the IInstructor interface.

    Note: While the methods implementation of this class might look identical to the Instructor class,
          they are still different roles by definition. In light of future development flexibility, we
          want to separate the two roles into different classes, in case role-specific methods are
          required in the future.
 */
public class NonStudentTA extends Member implements IInstructor {
    public NonStudentTA(int id, String cNetID) {
        super(id, cNetID);
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
