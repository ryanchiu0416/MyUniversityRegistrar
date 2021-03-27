package com.test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.src.client.NonStudentTA;
import com.src.client.StudentTA;
import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.viewgrade.Quarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests_nonSTA {
    NonStudentTA nonSTA = new NonStudentTA(3, "johnoop");

    // tests on instr functions
    @Test
    public void testFacSeeScheduleReport() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "MPCS500A : OOP Lab\n" +
                "   Location: Remote   |   17:30-19:30, Thursday\n";
        Assertions.assertEquals(expected, nonSTA.instrViewTeachingSchedule(new ReportFormatter()));
    }

    @Test
    public void testFacSeeScheduleTimesheet() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "*****************************************\n" +
                "Monday :\n" +
                "*****************************************\n" +
                "Tuesday :\n" +
                "*****************************************\n" +
                "Wednesday :\n" +
                "*****************************************\n" +
                "Thursday :  17:30-19:30 (MPCS500A @ Remote)\n" +
                "*****************************************\n" +
                "Friday :\n" +
                "*****************************************\n";
        Assertions.assertEquals(expected, nonSTA.instrViewTeachingSchedule(new TimesheetFormatter()));
    }

    @Test
    public void testFacGetStudentRoster() {
        String expected = "Roster for course : MPCS500A\n" +
                "2 Students:\n" +
                "  Ryan Chiu, studentID: 1, cNetID: rpchiu, Major: Computer Science\n" +
                "  Amy Quin, studentID: 6, cNetID: amyq, Major: Computer Science\n";
        Assertions.assertEquals(expected, nonSTA.instrGetClassRoster("MPCS500A"));
    }

    @Test
    public void testFacSendClassEmail() {
        String expected = "Email successfully sent as following:\n" +
                "\tFrom: john@uchicago.edu\n" +
                "\tTo: ryanpchiu@gmail.com, amy@gmail.com\n" +
                "\tMessage: 'hello everyone'\n";
        Assertions.assertEquals(expected, nonSTA.instrSendClassEmail("MPCS500A", "hello everyone"));
    }

    @Test
    public void testFacGetAndUploadGradeSheet() throws FileNotFoundException {
        String expected1 = "Gradesheet for MPCS500A has been created/updated at current directory.\n";
        Assertions.assertEquals(expected1, nonSTA.instrGetGradeSheet("MPCS500A", Quarter.winter, 2021));

        String expected2 = "Grades updated for course: MPCS500A\n";
        Assertions.assertEquals(expected2, nonSTA.instrUploadGradeSheet("MPCS500A", Quarter.winter, 2021, "gradesheet_MPCS500A_winter2021.txt"));
    }
}