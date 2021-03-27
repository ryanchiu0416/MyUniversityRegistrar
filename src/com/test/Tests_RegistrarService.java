package com.test;

import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.service.RegistrarService;
import com.src.viewgrade.Quarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Tests_RegistrarService {
    RegistrarService r = RegistrarService.getInstance();
    int sid = 1;
    int fid = 2;

    @Test
    public void testGetSingleGrade() {
        String expected = "MPCS100 : Intro to CS Lecture   AUTUMN 2020   Grade: A";
        Assertions.assertEquals(expected, r.getGrades(sid, "MPCS100").trim());
    }

    @Test
    public void testGetQuarterlyGrade() {
        String expected = "*********************************************\n" +
                "AUTUMN 2020\n" +
                "   MPCS100 : Intro to CS Lecture   Grade: A\n" +
                "   MPCS100A : Intro to CS Lab   Grade: A\n" +
                "*********************************************\n";

        Assertions.assertEquals(expected, r.getGrades(sid, Quarter.autumn, 2020));
    }

    @Test
    public void testGetAllGrades() {
        String expected = "*********************************************\n" +
                "WINTER 2021\n" +
                "   MPCS500 : OOP Lecture   Grade: IP\n" +
                "   MPCS500A : OOP Lab   Grade: IP\n" +
                "   MPCS550 : Intro to Computer Security   Grade: IP\n" +
                "   MPCS650 : Intro to Parellel Programming   Grade: IP\n" +
                "*********************************************\n" +
                "AUTUMN 2020\n" +
                "   MPCS100 : Intro to CS Lecture   Grade: A\n" +
                "   MPCS100A : Intro to CS Lab   Grade: A\n" +
                "*********************************************\n" +
                "SPRING 2019\n" +
                "   MPCS200 : Intro to Algo Lecture   Grade: B\n" +
                "   MPCS200C : Intro to Algo Lab   Grade: B\n" +
                "*********************************************\n";
        Assertions.assertEquals(expected, r.getGrades(sid));
    }

    @Test
    public void testGetTranscript() {
        String expected = "Name: Ryan Chiu\n" +
                "Student ID: 1\n" +
                "CNetID: rpchiu\n" +
                "Major: Computer Science\n" +
                "Department: Department of CS\n" +
                "*********************************************\n" +
                "WINTER 2021\n" +
                "   MPCS500 : OOP Lecture   Grade: IP\n" +
                "   MPCS500A : OOP Lab   Grade: IP\n" +
                "   MPCS550 : Intro to Computer Security   Grade: IP\n" +
                "   MPCS650 : Intro to Parellel Programming   Grade: IP\n" +
                "*********************************************\n" +
                "AUTUMN 2020\n" +
                "   MPCS100 : Intro to CS Lecture   Grade: A\n" +
                "   MPCS100A : Intro to CS Lab   Grade: A\n" +
                "*********************************************\n" +
                "SPRING 2019\n" +
                "   MPCS200 : Intro to Algo Lecture   Grade: B\n" +
                "   MPCS200C : Intro to Algo Lab   Grade: B\n" +
                "*********************************************\n";

        Assertions.assertEquals(expected, r.getTranscript(sid));
    }

    @Test
    public void testStudentScheduleReport() {
        String expected = "Student Schedule - WINTER 2021 (Report):\n" +
                "MPCS500 : OOP Lecture\n" +
                "   Location: Remote   |   17:30-19:30, Friday\n" +
                "MPCS500A : OOP Lab\n" +
                "   Location: Remote   |   17:30-19:30, Thursday\n" +
                "MPCS550 : Intro to Computer Security\n" +
                "   Location: Remote   |   14:30-16:30, Thursday\n" +
                "MPCS650 : Intro to Parellel Programming\n" +
                "   Location: Remote   |   16:30-18:30, Tuesday\n";
        Assertions.assertEquals(expected, r.getClassSchedule(sid, new ReportFormatter()));
    }

    @Test
    public void testStudentScheduleTimesheet() {
        String expected = "Student Schedule - WINTER 2021 (Report):\n" +
                "**********************************************************************************\n" +
                "Monday :\n" +
                "**********************************************************************************\n" +
                "Tuesday :  16:30-18:30 (MPCS650 @ Remote)\n" +
                "**********************************************************************************\n" +
                "Wednesday :\n" +
                "**********************************************************************************\n" +
                "Thursday :  14:30-16:30 (MPCS550 @ Remote), 17:30-19:30 (MPCS500A @ Remote)\n" +
                "**********************************************************************************\n" +
                "Friday :  17:30-19:30 (MPCS500 @ Remote)\n" +
                "**********************************************************************************\n";
        Assertions.assertEquals(expected, r.getClassSchedule(sid, new TimesheetFormatter()));
    }



    // instructor test
    @Test
    public void testInstructorScheduleReport() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "MPCS500 : OOP Lecture\n" +
                "   Location: Remote   |   17:30-19:30, Friday\n";
        Assertions.assertEquals(expected, r.getTeachingSchedule(fid, new ReportFormatter()));
    }

    @Test
    public void testInstructorScheduleTimesheet() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "*****************************************\n" +
                "Monday :\n" +
                "*****************************************\n" +
                "Tuesday :\n" +
                "*****************************************\n" +
                "Wednesday :\n" +
                "*****************************************\n" +
                "Thursday :\n" +
                "*****************************************\n" +
                "Friday :  17:30-19:30 (MPCS500 @ Remote)\n" +
                "*****************************************\n";
        Assertions.assertEquals(expected, r.getTeachingSchedule(fid, new TimesheetFormatter()));
    }

    @Test
    public void testSendCourseEmail() {
        String courseID = "MPCS500";
        Assertions.assertEquals("Email successfully sent as following:\n" +
                "\tFrom: mark@uchicago.edu\n" +
                "\tTo: ryanpchiu@gmail.com, amy@gmail.com, bobby@gmail.com, mary@gmail.com\n" +
                "\tMessage: 'hello'\n", r.sendClassEmail(fid, courseID, "hello"));
    }

    @Test
    public void testSendCourseEmailNotPermitted() {
        String courseID = "MPCS200";
        Assertions.assertEquals("Permission denied.\n", r.sendClassEmail(fid, courseID, "hello"));
    }

    @Test
    public void testGetRoster() {
        String expected = "Roster for course : MPCS500\n" +
                "4 Students:\n" +
                "  Ryan Chiu, studentID: 1, cNetID: rpchiu, Major: Computer Science\n" +
                "  Amy Quin, studentID: 6, cNetID: amyq, Major: Computer Science\n" +
                "  Bobby John, studentID: 7, cNetID: bob, Major: Computer Science\n" +
                "  Mary Kreg, studentID: 8, cNetID: maryk, Major: Computer Science\n";

        String courseID = "MPCS500";
        Assertions.assertEquals(expected, r.getRoster(fid, courseID));
    }

    @Test
    public void testGetRosterNotPermitted() {
        String expected = "Permission denied.\n";
        String courseID = "MPCS000";
        Assertions.assertEquals(expected, r.getRoster(fid, courseID));
    }

    @Test
    public void testGenerateAndUpdateGradeSheetNotPermitted() throws FileNotFoundException {
        String courseID = "MPCS000";
        Assertions.assertEquals("Permission denied.\n", r.generateGradeSheet(fid, courseID, Quarter.winter, 2021));

        String expected = "Permission denied.\n";
        String filePath = "gradesheet_MPCS500_winter2021.txt";
        Assertions.assertEquals(expected, r.updateGrades(fid, "MPCS500", filePath, Quarter.summer, 2021));
        Assertions.assertEquals(expected, r.updateGrades(fid, "MPCS500", filePath, Quarter.winter, 2020));
    }

    @Test
    public void testGenerateAndUpdateGradeSheet() throws FileNotFoundException {
        String courseID = "MPCS500";
        Assertions.assertEquals("Gradesheet for " + courseID + " has been created/updated at current directory.\n", r.generateGradeSheet(fid, courseID, Quarter.winter, 2021));

        String expected = "Grades updated for course: MPCS500\n";
        String filePath = "gradesheet_MPCS500_winter2021.txt";
        Assertions.assertEquals(expected, r.updateGrades(fid, "MPCS500", filePath, Quarter.winter, 2021));
    }

    @Test
    public void testFacUploadWrongGradeSheet() throws FileNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        r.updateGrades(fid, "MPCS500", "gradesheet_test_wrongHeader.txt", Quarter.winter, 2021);
        String expected = "Error: the gradesheet provided does not match the course specification (courseID, quarter, or year).\n";
        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    public void testFacUploadWrongGradeFormat() throws FileNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        r.updateGrades(fid, "MPCS500", "gradesheet_test_wrongGradeFormat.txt", Quarter.winter, 2021);
        String expected = "Grades can only be one of: 'A', 'B', 'C', 'D', 'F', 'I', 'P', 'IP'.\n";
        Assertions.assertEquals(expected, outContent.toString());
    }
}
