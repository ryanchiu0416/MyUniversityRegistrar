package com.test;

import com.src.client.Student;
import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.viewgrade.Quarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests_StudentClient {
    Student studClient = new Student(1, "rpchiu");

    @Test
    public void testStudSeeSingleGrade() {
        String expected = "MPCS100 : Intro to CS Lecture   AUTUMN 2020   Grade: A\n";
        Assertions.assertEquals(expected, studClient.studViewGrade("MPCS100"));
    }

    @Test
    public void testStudSeeQuarterGrades() {
        String expected = "*********************************************\n" +
                "AUTUMN 2020\n" +
                "   MPCS100 : Intro to CS Lecture   Grade: A\n" +
                "   MPCS100A : Intro to CS Lab   Grade: A\n" +
                "*********************************************\n";
        Assertions.assertEquals(expected, studClient.studViewQuarterlyGrades(Quarter.autumn, 2020));
    }

    @Test
    public void testStudSeeAllGrades() {
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
        Assertions.assertEquals(expected, studClient.studViewAllGrades());
    }

    @Test
    public void testStudSeeRestrictions() {
        String expected = "******Restriction Summary for studentID = 1******\n" +
                "Restriction ID: 100\n" +
                "Cause: fail to pay tuition\n" +
                "Issued Date: 02/05/2020\n" +
                "In effect?: NO \n" +
                "\n" +
                "Restriction ID: 101\n" +
                "Cause: fail to fill out immunization\n" +
                "Issued Date: 02/15/2020\n" +
                "In effect?: YES \n" +
                "**************************************************\n" +
                "**********Student ID = 1 CANNOT REGISTER**********\n" +
                "**************************************************\n";
        Assertions.assertEquals(expected, studClient.studViewRestrictionStatus());
    }

    @Test
    public void testStudSeeScheduleReport() {
        String expected = "Student Schedule - WINTER 2021 (Report):\n" +
                "MPCS500 : OOP Lecture\n" +
                "   Location: Remote   |   17:30-19:30, Friday\n" +
                "MPCS500A : OOP Lab\n" +
                "   Location: Remote   |   17:30-19:30, Thursday\n" +
                "MPCS550 : Intro to Computer Security\n" +
                "   Location: Remote   |   14:30-16:30, Thursday\n" +
                "MPCS650 : Intro to Parellel Programming\n" +
                "   Location: Remote   |   16:30-18:30, Tuesday\n";
        Assertions.assertEquals(expected, studClient.studViewStudentSchedule(new ReportFormatter()));
    }

    @Test
    public void testStudSeeScheduleTimesheet() {
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
        Assertions.assertEquals(expected, studClient.studViewStudentSchedule(new TimesheetFormatter()));
    }

    @Test
    public void testStudSeeTranscript() {
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
        Assertions.assertEquals(expected, studClient.studViewOfficialTranscript());
    }
}