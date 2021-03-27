package com.src.courseemail;

import com.src.db.QueryEngine;
import java.util.List;
/*
    CourseEmailSender class handles tasks related to sending course email to all students.
    Main operation(s): send email to all students in the desired class.

    Pattern used: Singleton
*/
public class CourseEmailSender {
    private static CourseEmailSender instance = null;
    public static CourseEmailSender getInstance() {
        if (instance == null) {
            instance = new CourseEmailSender();
        }
        return instance;
    }
    private CourseEmailSender() {}

    public String sendEmail(int instructorID, String message, String courseID) {
        // fetch instructor's email from DB
        String fromEmail = QueryEngine.getInstance().instrGetInstructorEmail(instructorID);

        // Fetch all student emails in corresponding course
        List<String> toEmails = QueryEngine.getInstance().instrGetStudentEmailInClass(courseID);

        if (fromEmail == null || toEmails == null) {
            return "Error occur: email(s) cannot be fetched.\n";
        }
        return QueryEngine.getInstance().instrSendEmail(fromEmail, toEmails, message);
    }
}