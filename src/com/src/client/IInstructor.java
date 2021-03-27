package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.viewgrade.Quarter;

import java.io.FileNotFoundException;

/*
    The IInstructor interface defines core operations for Instructor roles. Main responsibilities include
    view teaching schedule, get roster, send email, get grade sheet, and upload grade sheet.
*/
public interface IInstructor {
    // View schedule of current quarter as an instructor
    // Input: FormatStrategy - Report format / Time-sheet format
    // Output: Text representation of current quarter's schedule in the desired format.
    String instrViewTeachingSchedule(FormatStrategy formatter);

    // View roster of all students enrolled in the course specified
    // Input: courseID of the course to look up in
    // Output: Text representation of the roster with basic student info.
    String instrGetClassRoster(String courseID);

    // Send emails to all students in the course specified
    // Input: courseID of the course, and a message to be sent
    // Output: A string feedback indicating the status of the operation.
    String instrSendClassEmail(String courseID, String message);

    // Obtain a grade sheet in the course specified
    // Input: courseID of the course, quarter, and year of the course.
    // Output: A string feedback indicating the status of the operation.
    String instrGetGradeSheet(String courseID, Quarter q, int year) throws FileNotFoundException;

    // Upload a grade sheet of the course specified
    // Input: courseID of the course, quarter, and year of the course, and filepath to the gradesheet.
    // Output: A string feedback indicating the status of the operation.
    String instrUploadGradeSheet(String courseID, Quarter q, int year, String filePath) throws FileNotFoundException;

    // View all course taught & teaching
    // Input: None
    // Output: string - all courses teaching now and taught before
    String instrGetAllCourseTaught();
}
