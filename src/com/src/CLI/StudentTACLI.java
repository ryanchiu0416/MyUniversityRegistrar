package com.src.CLI;

import com.src.client.StudentTA;
import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.viewgrade.Quarter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
    StudentTACLI implements CLIStrategy interface, and handles generating menus for Student TAs.
    Methods include presenting front-end menu for users and execute commands entered.

    Pattern: Strategy (concrete subclass)
 */
public class StudentTACLI implements CLIStrategy {
    private StudentTA user;
    private String name;
    public StudentTACLI(StudentTA f, String name) {
        this.user = f;
        this.name = name;
    }

    @Override
    public void menu() throws IOException {
        String response = "";
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!response.equals("Goodbye!")) {
            System.out.println();
            System.out.println(" *** Hi " + this.name + ". You're logged in to Registrar System as Student TA *** ");
            System.out.println("### Student operations menu ###");
            System.out.println("> grade single <courseID>");
            System.out.println("> grade quarter <quarter=['autumn', 'winter', 'spring', 'summer']> <year>");
            System.out.println("> grade all");
            System.out.println("> restriction");
            System.out.println("> stud_schedule <'r' = report or 't' = timesheet>");
            System.out.println("> transcript");
            System.out.println("### Instructor operations menu ###");
            System.out.println("> roster <courseID>");
            System.out.println("> sendEmail <courseID> <message (can contain space) >");
            System.out.println("> instr_schedule <'r' = report or 't' = timesheet>");
            System.out.println("> courseTaughtHistory");
            System.out.println("> gradesheet get <courseID> <quarter=['autumn', 'winter', 'spring', 'summer']> <year>");
            System.out.println("> gradesheet post <courseID> <quarter=['autumn', 'winter', 'spring', 'summer']> <year> <filePath>");
            System.out.println("> quit");
            input = sc.nextLine().trim();
            response = execute(input);
            System.out.print(response);
        }
    }

    @Override
    public String execute(String command) throws FileNotFoundException {
        String[] tokens = tokenize(command);
        String response = "";
        if (tokens.length == 0) {
            response = "Please enter a command";
        } else if (tokens[0].equals("quit")) {
            response = "Goodbye!";
        }

        // Student operations below:
        else if (tokens[0].equals("grade")) {
            if (tokens[1].equals("single")) {
                if (tokens.length == 3) {
                    response = user.studViewGrade(tokens[2]);
                } else {
                    response = "Please provide a courseID\n";
                }
            } else if (tokens[1].equals("quarter")) {
                if (tokens.length == 4) {
                    response = user.studViewQuarterlyGrades(Quarter.valueOf(tokens[2].toLowerCase()),
                            Integer.parseInt(tokens[3]));
                } else {
                    response = "Please provide a quarter and a year\n";
                }
            } else if (tokens[1].equals("all")) {
                if (tokens.length == 2) {
                    response = user.studViewAllGrades();
                } else {
                    response = "This command does not require any parameter\n";
                }
            } else {
                response = "Unrecognized command 'grade " + tokens[1] + "'\n";
            }
        } else if (tokens[0].equals("restriction")) {
            response = user.studViewRestrictionStatus();
        } else if (tokens[0].equals("stud_schedule")) {
            if (tokens.length == 2) {
                if (tokens[1].equals("r")) {
                    response = user.studViewStudentSchedule(new ReportFormatter());
                } else if (tokens[1].equals("t")) {
                    response = user.studViewStudentSchedule(new TimesheetFormatter());
                } else {
                    response = "Unrecognized parameter '" + tokens[1] + "'\n";
                }
            } else {
                response = "Input parameter error\n";
            }
        } else if (tokens[0].equals("transcript")) {
            response = user.studViewOfficialTranscript();
        }

        // Instructor operations below:
        else if (tokens[0].equals("roster")) {
            if (tokens.length == 2) {
                response = user.instrGetClassRoster(tokens[1]);
            } else {
                response = "Please provide a courseID\n";
            }
        } else if (tokens[0].equals("sendEmail")) {
            if (tokens.length <= 2) {
                response = "Please provide a courseID and a message\n";
            } else {
                String courseID = tokens[1];
                String msg = String.join(" ",  Arrays.copyOfRange(tokens, 2, tokens.length));
                response = user.instrSendClassEmail(courseID, msg);
            }
        } else if (tokens[0].equals("instr_schedule")) {
            if (tokens.length == 2) {
                if (tokens[1].equals("r")) {
                    response = user.instrViewTeachingSchedule(new ReportFormatter());
                } else if (tokens[1].equals("t")) {
                    response = user.instrViewTeachingSchedule(new TimesheetFormatter());
                } else {
                    response = "Unrecognized parameter '" + tokens[1] + "'\n";
                }
            } else {
                response = "Input parameter error\n";
            }
        } else if (tokens[0].equals("gradesheet")) {
            if (tokens[1].equals("get") && tokens.length == 5) {
                response = user.instrGetGradeSheet(tokens[2], Quarter.valueOf(tokens[3]), Integer.parseInt(tokens[4]));
            } else if (tokens[1].equals("post") && tokens.length > 3) {
                String path = command.substring(command.indexOf(tokens[4]) + tokens[4].length()).trim();
                response = user.instrUploadGradeSheet(tokens[2], Quarter.valueOf(tokens[3]), Integer.parseInt(tokens[4]), path);
            } else {
                response = "Input Parameter Error\n";
            }
        } else if (tokens[0].equals("courseTaughtHistory") && tokens.length == 1) {
            response = user.instrGetAllCourseTaught();
        } else {
            response = "Error: unrecognized command '" + tokens[0] + "'\n";
        }
        return response;
    }

    @Override
    public String[] tokenize(String command) {
        return command.split("\\s+");
    }
}
