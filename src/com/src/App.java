package com.src;

/*
    App class serves as the "main" that runs the Registrar Service application.
    The application will be run as a command-line based app.
    Main operations consists of logging users in, presenting user menu, etc.
 */

import com.src.CLI.*;
import com.src.client.Faculty;
import com.src.client.NonStudentTA;
import com.src.client.Student;
import com.src.client.StudentTA;
import com.src.db.QueryEngine;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to University of Chicago Registrar System!");

        CLIStrategy currCLI = null;
        String cNetID = "";
        String password = "";

        while (currCLI == null) {
            System.out.print("Please enter your CNetID: ");
            Scanner sc = new Scanner(System.in);
            cNetID = sc.nextLine();
            System.out.print("Please enter your password: ");
            password = sc.nextLine();
            currCLI = isLoginCorrect(cNetID, password);
        }

        // Delegate to concrete subclasses.
        currCLI.menu();
    }

    public static CLIStrategy isLoginCorrect(String cNetID, String password) {
        CLIStrategy currCLI = null;
        String[] loginRes = QueryEngine.getInstance().isUserCredentialCorrect(cNetID, password);
        if (loginRes != null) {
            int id = Integer.parseInt(loginRes[0]);
            String role = loginRes[1];
            String name = loginRes[2];
            if (role.equals("student")) {
                currCLI = new StudentCLI(new Student(id, cNetID), name);
            } else if (role.equals("faculty")) {
                currCLI = new FacultyCLI(new Faculty(id, cNetID), name);
            } else if (role.equals("studentTA")) {
                currCLI = new StudentTACLI(new StudentTA(id, cNetID), name);
            } else if (role.equals("nonStudentTA")) {
                currCLI = new NonStudentTACLI(new NonStudentTA(id, cNetID), name);
            } else {
                // This case will not occur, since the project scope has assumed database data are correct.
                System.out.println("Server Error: unrecognized role. Please contact UChicago registrar.\n");
            }
        } else {
            System.out.println("Error: login error. Please try again.\n");
        }
        return currCLI;
    }
}