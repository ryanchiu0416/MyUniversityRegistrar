package com.src.assigngrade;

import com.src.db.QueryEngine;
import com.src.viewgrade.Quarter;
import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/*
    GradeAssignmentEngine class handles tasks related to grade assignment.
    Main operations: generate grade sheet, and update grades from an uploaded grade sheet.

    Pattern used: Singleton
*/
public class GradeAssignmentEngine {
    private static GradeAssignmentEngine instance = null;
    public static GradeAssignmentEngine getInstance() {
        if (instance == null) {
            instance = new GradeAssignmentEngine();
        }
        return instance;
    }
    private GradeAssignmentEngine() {}


    public String generateGradeSheet(String courseID, Quarter q, int year) throws FileNotFoundException {
        Map<Integer, String[]> grades = QueryEngine.getInstance().instrFetchGrades(courseID, q, year);
        String fileName = "gradesheet_" + courseID + "_" + q + year + ".txt";
        PrintStream writer = new PrintStream(System.getProperty("user.dir") + "/" + fileName);
        writer.println(courseID + " " + q + " " + year);
        writer.println("ID \t\t Grade \t\t Name");
        for (int id : grades.keySet()) {
            String[] arr = grades.get(id);
            writer.println(id + " \t\t " + arr[1] + " \t\t " + arr[0]);
        }
        return "Gradesheet for " + courseID + " has been created/updated at current directory.\n";
    }

    public String importGradesFromFile(String courseID, String path, Quarter q, int year) {
        Map<Integer, String> grades = readImportedGradeSheet(new File(path), courseID, q, year);
        if (grades == null) {
            return "";
        }
        return QueryEngine.getInstance().instrWriteGrades(courseID, grades, q, year);
    }

    private Map<Integer, String> readImportedGradeSheet(File file, String courseID, Quarter q, int year) {
        try {
            // read from imported grade sheet and upload to DB
            Map<Integer, String> grades = new HashMap<>();
            Scanner sc = new Scanner(file);
            String[] header = sc.nextLine().split("\\s+");
            if (!courseID.equals(header[0]) || !q.toString().equals(header[1]) || year != Integer.parseInt(header[2])) {
                throw new IllegalArgumentException();
            }
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] lineItems = sc.nextLine().split("\\s+");
                int id = Integer.parseInt(lineItems[0]);
                String grade = lineItems[1];
                // Grade can only be: 'A', 'B', 'C', 'D', 'F', 'I', 'P' or "IP" (In-Progress)
                // - choose not to leave blank for "In-Progress" because blank display might make user think there's error.
                if (!grade.equals("IP") && (grade.length() != 1 || !"ABCPDFI".contains(grade))) {
                    throw new InputMismatchException();
                }
                grades.put(id, grade);
            }
            return grades;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof  IllegalArgumentException) {
                System.out.println("Error: the gradesheet provided does not match the course specification (courseID, quarter, or year).");
            } else if (e instanceof InputMismatchException) {
                System.out.println("Grades can only be one of: 'A', 'B', 'C', 'D', 'F', 'I', 'P', 'IP'.");
            } else {
                System.out.println("Error occurred when importing grades. Please make sure file exists.");
            }
            return null;
        }
    }
}