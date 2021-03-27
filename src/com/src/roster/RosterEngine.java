package com.src.roster;

import com.src.db.QueryEngine;
import com.src.transcript.StudentProfile;
import java.util.List;
/*
    RosterEngine class handles tasks related to class roster.
    Main operation(s): generate roster for a specific course.

    Pattern used: Singleton
*/
public class RosterEngine {
    private static RosterEngine instance = null;
    public static RosterEngine getInstance() {
        if (instance == null) {
            instance = new RosterEngine();
        }
        return instance;
    }
    private RosterEngine() {}


    public Roster generateRoster(String courseID) {
        List<StudentProfile> students = QueryEngine.getInstance().instrGetStudentProfilesInClass(courseID);
        return new Roster.Builder(courseID).addStudents(students).build();
    }
}