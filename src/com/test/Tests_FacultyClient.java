package com.test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.src.client.Faculty;
import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.viewgrade.Quarter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests_FacultyClient {
    Faculty faculty = new Faculty(2, "mark");

    @Test
    public void testFacSeeScheduleReport() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "MPCS500 : OOP Lecture\n" +
                "   Location: Remote   |   17:30-19:30, Friday\n";
        Assertions.assertEquals(expected, faculty.instrViewTeachingSchedule(new ReportFormatter()));
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
                "Thursday :\n" +
                "*****************************************\n" +
                "Friday :  17:30-19:30 (MPCS500 @ Remote)\n" +
                "*****************************************\n";
        Assertions.assertEquals(expected, faculty.instrViewTeachingSchedule(new TimesheetFormatter()));
    }

    @Test
    public void testFacGetStudentRoster() {
        String expected = "Roster for course : MPCS500\n" +
                "4 Students:\n" +
                "  Ryan Chiu, studentID: 1, cNetID: rpchiu, Major: Computer Science\n" +
                "  Amy Quin, studentID: 6, cNetID: amyq, Major: Computer Science\n" +
                "  Bobby John, studentID: 7, cNetID: bob, Major: Computer Science\n" +
                "  Mary Kreg, studentID: 8, cNetID: maryk, Major: Computer Science\n";
        Assertions.assertEquals(expected, faculty.instrGetClassRoster("MPCS500"));
    }

    @Test
    public void testFacSendClassEmail() {
        String expected = "Email successfully sent as following:\n" +
                "\tFrom: mark@uchicago.edu\n" +
                "\tTo: ryanpchiu@gmail.com, amy@gmail.com, bobby@gmail.com, mary@gmail.com\n" +
                "\tMessage: 'hello everyone'\n";
        Assertions.assertEquals(expected, faculty.instrSendClassEmail("MPCS500", "hello everyone"));
    }

    @Test
    public void testFacGetAndUploadGradeSheet() throws FileNotFoundException {
        String expected1 = "Gradesheet for MPCS200 has been created/updated at current directory.\n";
        Assertions.assertEquals(expected1, faculty.instrGetGradeSheet("MPCS200", Quarter.spring, 2019));

        String expected2 = "Grades updated for course: MPCS200\n";
        Assertions.assertEquals(expected2, faculty.instrUploadGradeSheet("MPCS200", Quarter.spring, 2019, "gradesheet_MPCS200_spring2019.txt"));
    }
}