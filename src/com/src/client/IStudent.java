package com.src.client;

import com.src.schedule.FormatStrategy;
import com.src.viewgrade.Quarter;

/*
    The IStudent interface defines core functionality of student roles. Key operation includes view single/quarterly/
    all grades, view restriction status, view student schedule, and view official transcript.
 */
public interface IStudent {
    // View grade from a specific course
    // Input: courseID of the course whose grade is desired
    // Output: Text representation of a grade corresponding to the class.
    String studViewGrade(String courseID);

    // View grade from a specific quarter in the past
    // Input: Quarter and year of the specific quarter whose grades are desired
    // Output: Text representation of a list of grades corresponding to the quarter, year.
    String studViewQuarterlyGrades(Quarter q, int year);

    // View all past grades
    // Input: None
    // Output: Text representation of all grades on record.
    String studViewAllGrades();

    // View history of all restriction and current status
    // Input: None
    // Output: Text representation of a list of restriction with details, and one's current registration status.
    String studViewRestrictionStatus();

    // View schedule of current quarter as a student
    // Input: FormatStrategy - Report format / Time-sheet format
    // Output: Text representation of current quarter's schedule in the desired format.
    String studViewStudentSchedule(FormatStrategy formatter);

    // View the official transcript with all records accrued.
    // Input: None
    // Output: Text representation of the official transcript, including basic student info, courses taken and grades.
    String studViewOfficialTranscript();
}
