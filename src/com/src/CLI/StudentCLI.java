package com.src.CLI;

import com.src.client.Student;
import com.src.schedule.ReportFormatter;
import com.src.schedule.TimesheetFormatter;
import com.src.viewgrade.Quarter;
import java.util.Scanner;
/*
    StudentCLI implements CLIStrategy interface, and handles generating menus for students.
    Methods include presenting front-end menu for users and execute commands entered.

    Pattern: Strategy (concrete subclass)
 */
public class StudentCLI implements CLIStrategy {
    private Student user;
    private String name;
    public StudentCLI(Student s, String name) {
        this.user = s;
        this.name = name;
    }

    @Override
    public void menu() {
        String response = "";
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!response.equals("Goodbye!")) {
            System.out.println();
            System.out.println(" *** Hi " + this.name + ". You're logged in to Registrar System as Student *** ");
            System.out.println("> grade single <courseID>");
            System.out.println("> grade quarter <quarter=['autumn', 'winter', 'spring', 'summer']> <year>");
            System.out.println("> grade all");
            System.out.println("> restriction");
            System.out.println("> schedule <'r' = report or 't' = timesheet>");
            System.out.println("> transcript");
            System.out.println("> quit");
            input = sc.nextLine().trim();
            response = execute(input);
            System.out.print(response);
        }
    }

    @Override
    public String execute(String command) {
        String[] tokens = tokenize(command);
        String response = "";
        if (tokens.length == 0) {
            response = "Please enter a command";
        } else if (tokens[0].equals("quit")) {
            response = "Goodbye!";
        } else if (tokens[0].equals("grade")) {
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
        } else if (tokens[0].equals("schedule")) {
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