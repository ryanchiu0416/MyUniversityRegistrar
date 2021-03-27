package com.src.transcript;

import com.src.db.QueryEngine;
import com.src.viewgrade.AllGradeFetch;

/*
    ScheduleEngine class handles tasks related to generating official transcript for students.

    Pattern used: Singleton
*/
public class TranscriptGenerator {
    private static TranscriptGenerator instance = null;
    public static TranscriptGenerator getInstance() {
        if (instance == null) {
            instance = new TranscriptGenerator();
        }
        return instance;
    }
    private TranscriptGenerator() {}


    public String generateTranscript(int studentID) {
        StudentProfile basicInfo = QueryEngine.getInstance().studGetFullProfile(studentID);
        if (basicInfo == null) {
            return "Student with ID = " + studentID + " cannot be found!";
        }

        String grades = fetchGradeData(studentID);
        return basicInfo.toString() + grades;
    }

    private String fetchGradeData(int studentID) {
        return new AllGradeFetch(studentID).fetchGrade();
    }
}