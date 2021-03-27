
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

public class Tests_STA {
    StudentTA sTA = new StudentTA(4, "jeffoop");

    // tests on instr functions
    @Test
    public void testFacSeeScheduleReport() {
        String expected = "Instructor Schedule - WINTER 2021 (Report):\n" +
                "MPCS500B : OOP Lab\n" +
                "   Location: Remote   |   17:30-19:30, Thursday\n";
        Assertions.assertEquals(expected, sTA.instrViewTeachingSchedule(new ReportFormatter()));
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
                "Thursday :  17:30-19:30 (MPCS500B @ Remote)\n" +
                "*****************************************\n" +
                "Friday :\n" +
                "*****************************************\n";
        Assertions.assertEquals(expected, sTA.instrViewTeachingSchedule(new TimesheetFormatter()));
    }

    @Test
    public void testFacGetStudentRoster() {
        String expected = "Roster for course : MPCS500B\n" +
                "2 Students:\n" +
                "  Bobby John, studentID: 7, cNetID: bob, Major: Computer Science\n" +
                "  Mary Kreg, studentID: 8, cNetID: maryk, Major: Computer Science\n";
        Assertions.assertEquals(expected, sTA.instrGetClassRoster("MPCS500B"));
    }

    @Test
    public void testFacSendClassEmail() {
        String expected = "Email successfully sent as following:\n" +
                "\tFrom: jeff@uchicago.edu\n" +
                "\tTo: bobby@gmail.com, mary@gmail.com\n" +
                "\tMessage: 'hello everyone'\n";
        Assertions.assertEquals(expected, sTA.instrSendClassEmail("MPCS500B", "hello everyone"));
    }

    @Test
    public void testFacGetAndUploadGradeSheet() throws FileNotFoundException {
        String expected1 = "Gradesheet for MPCS500B has been created/updated at current directory.\n";
        Assertions.assertEquals(expected1, sTA.instrGetGradeSheet("MPCS500B", Quarter.winter, 2021));

        String expected2 = "Grades updated for course: MPCS500B\n";
        Assertions.assertEquals(expected2, sTA.instrUploadGradeSheet("MPCS500B", Quarter.winter, 2021, "gradesheet_MPCS500B_winter2021.txt"));
    }



    // tests on student functions
    @Test
    public void testStudSeeSingleGrade() {
        String expected = "MPCS800 : Advanced Data Science Lecture   WINTER 2021   Grade: IP\n";
        Assertions.assertEquals(expected, sTA.studViewGrade("MPCS800"));
    }

    @Test
    public void testStudSeeQuarterGrades() {
        String expected = "*********************************************\n" +
                "SPRING 2019\n" +
                "   MPCS200 : Intro to Algo Lecture   Grade: A\n" +
                "   MPCS200C : Intro to Algo Lab   Grade: A\n" +
                "*********************************************\n";
        Assertions.assertEquals(expected, sTA.studViewQuarterlyGrades(Quarter.spring, 2019));
    }

    @Test
    public void testStudSeeAllGrades() {
        String expected = "*********************************************\n" +
                "WINTER 2021\n" +
                "   MPCS800 : Advanced Data Science Lecture   Grade: IP\n" +
                "   MPCS800A : Advanced Data Science Lab   Grade: IP\n" +
                "*********************************************\n" +
                "SPRING 2019\n" +
                "   MPCS200 : Intro to Algo Lecture   Grade: A\n" +
                "   MPCS200C : Intro to Algo Lab   Grade: A\n" +
                "*********************************************\n";
        Assertions.assertEquals(expected, sTA.studViewAllGrades());
    }

    @Test
    public void testStudSeeRestrictions() {
        String expected = "******Restriction Summary for studentID = 4******\n" +
                "**********Student ID = 4 CAN REGISTER**********\n";
        Assertions.assertEquals(expected, sTA.studViewRestrictionStatus());
    }

    @Test
    public void testStudSeeScheduleReport() {
        String expected = "Student Schedule - WINTER 2021 (Report):\n" +
                "MPCS800 : Advanced Data Science Lecture\n" +
                "   Location: Remote   |   17:30-19:30, Friday\n" +
                "MPCS800A : Advanced Data Science Lab\n" +
                "   Location: Remote   |   17:30-19:30, Thursday\n";
        Assertions.assertEquals(expected, sTA.studViewStudentSchedule(new ReportFormatter()));
    }

    @Test
    public void testStudSeeScheduleTimesheet() {
        String expected = "Student Schedule - WINTER 2021 (Report):\n" +
                "*****************************************\n" +
                "Monday :\n" +
                "*****************************************\n" +
                "Tuesday :\n" +
                "*****************************************\n" +
                "Wednesday :\n" +
                "*****************************************\n" +
                "Thursday :  17:30-19:30 (MPCS800A @ Remote)\n" +
                "*****************************************\n" +
                "Friday :  17:30-19:30 (MPCS800 @ Remote)\n" +
                "*****************************************\n";
        Assertions.assertEquals(expected, sTA.studViewStudentSchedule(new TimesheetFormatter()));
    }

    @Test
    public void testStudSeeTranscript() {
        String expected = "Name: Jeff\n" +
                "Student ID: 4\n" +
                "CNetID: jeffoop\n" +
                "Major: Computer Science\n" +
                "Department: Department of CS\n" +
                "*********************************************\n" +
                "WINTER 2021\n" +
                "   MPCS800 : Advanced Data Science Lecture   Grade: IP\n" +
                "   MPCS800A : Advanced Data Science Lab   Grade: IP\n" +
                "*********************************************\n" +
                "SPRING 2019\n" +
                "   MPCS200 : Intro to Algo Lecture   Grade: A\n" +
                "   MPCS200C : Intro to Algo Lab   Grade: A\n" +
                "*********************************************\n";
        Assertions.assertEquals(expected, sTA.studViewOfficialTranscript());
    }

}