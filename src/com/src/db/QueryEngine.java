package com.src.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.src.Reference;
import com.src.restriction.StudentRestriction;
import com.src.transcript.StudentProfile;
import com.src.schedule.Course;
import com.src.viewgrade.Quarter;
import org.bson.Document;

import java.util.*;
import java.sql.*;

/*
    QueryEngine class handles tasks related to fetching data from MYSQL database.
    It has many operations that support the interaction between the system and the database.

    Pattern used: Singleton
*/

public class QueryEngine {
    private Connection mySQLConn;
    private MongoDatabase mongoDB;

    private static QueryEngine instance = null;
    public static QueryEngine getInstance() {
        if (instance == null) {
            instance = new QueryEngine();
        }
        return instance;
    }
    private QueryEngine() {
        // setup MYSQL and Mongo connection
        try {
            this.mySQLConn = DriverManager.getConnection(Reference.getMysqlUrl(), Reference.getMysqlUsername(),
                                                    Reference.getMysqlPassword());
            this.mongoDB = new MongoClient(Reference.getMongodbHostname(), Reference.getMongodbPort())
                                .getDatabase(Reference.getMongodbDbname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*
        MongoDB Operations
     */
    // Check if a given set of user credential matches any in the database
    // Return a String[] = {id, role, name}. Null if no match is found.
    public String[] isUserCredentialCorrect(String cNetID, String password) {
        try {
            String[] res = null;
            MongoCollection<Document> coll = mongoDB.getCollection("userlogin");
            String correctPass = "";
            String role = "";
            String id = "";
            String name = "";
            for (Document curr : coll.find(Filters.eq("cNetID", cNetID))) {
                correctPass = curr.get("password").toString();
                role = curr.get("role").toString();
                id = curr.get("id").toString();
                name = curr.get("name").toString();
            }

            if (password.equals(correctPass)) {
                res = new String[] {id, role, name};
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //=====================================================================================
    /*
        MySQL Operations
     */

    /*
        STUDENT
    */
    // TRANSCRIPT
    // Need to fetch full profile for transcript.
    public StudentProfile studGetFullProfile(int studentID) {
        try {
            Statement myStmt = mySQLConn.createStatement();

            ResultSet rs = myStmt.executeQuery("select * from Student s join Member m on s.sid = m.id" +
                    " where s.sid = " + studentID);
            StudentProfile s = null;
            while (rs.next()) {
                s = new StudentProfile.Builder(rs.getInt("sid")).addName(rs.getString("name"))
                        .addCNetID(rs.getString("cNetID")).addMajor(rs.getString("major"))
                        .addDepartment(rs.getString("department")).addEmail(rs.getString("email"))
                        .build();
            }
            rs.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // VIEW GRADES
    public String[] studFetchSingleGrade(int studentID, String courseID) {
        try {
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select c.cid cid, c.courseName cName, e.quarter as q, e.year as yr, e.grade as grade" +
                    " from Enrollment as e join Course as c on e.cid = c.cid where sid=" + studentID + " and e.cid = '" + courseID + "';");
            String[] gradeItem = null;
            while (rs.next()) {
                gradeItem = new String[3];
                gradeItem[0] = rs.getString("cid") + " : " + rs.getString("cName");
                gradeItem[1] = rs.getString("q") + " " + rs.getString("yr");
                gradeItem[2] = rs.getString("grade");
            }
            rs.close();
            return gradeItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> studFetchQuarterlyGrades(int studentID, Quarter qtr, int year) {
        try {
            List<String[]> lst = new ArrayList<>();
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select c.cid cid, c.courseName cName, e.quarter as q, e.year as yr, e.grade as grade"
                    + " from Enrollment as e join Course as c on e.cid = c.cid where sid=" + studentID + " and e.quarter = '" + qtr.toString()
                    + "' and e.year = " + year + ";");
            while (rs.next()) {
                lst.add(new String[] {rs.getString("cid") + " : " + rs.getString("cName"), rs.getString("grade")});
            }
            rs.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // also used when generating transcript
    public Map<String, List<String[]>> studFetchAllGrades(int studentID) {
        try {
            Map<String, List<String[]>> grades = new HashMap<>();
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select * " +
            " from Enrollment as e join Course as c on e.cid = c.cid where sid=" + studentID + ";");
            while (rs.next()) {
                String key = rs.getString("quarter").toUpperCase() + " " + rs.getString("year");
                if (!grades.containsKey(key)) {
                    grades.put(key, new ArrayList<>());
                }
                grades.get(key).add(new String[] {rs.getString("cid") + " : " + rs.getString("courseName"),
                                                  rs.getString("grade")});
            }
            rs.close();
            return grades;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    // VIEW RESTRICTION
    public List<StudentRestriction> studFetchRestrictions(int studentID) {
        try {
            List<StudentRestriction> lst = new ArrayList<>();
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Restriction where sid = " + studentID);
            while (rs.next()) {
                lst.add(new StudentRestriction.Builder(rs.getInt("rid")).addStudentID(rs.getInt("sid")).addCause(rs.getString("cause"))
                        .addDateIssued(rs.getString("dateIssued")).addHasLifted(rs.getBoolean("hasLifted")).build());
            }
            rs.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    // SCHEDULE
    // return a list of Course objects a student is enrolling in now.
    public List<Course> studCourseCurrEnrollment(int studentID) {
        try {
            List<Course> lst = new ArrayList<>();
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Enrollment as e join Course as c on e.cid=c.cid where sid = "
                            + studentID + " and quarter = '" + Reference.getQuarter().toString()
                            + "' and year = " + Reference.getYear());
            while (rs.next()) {
                lst.add(new Course.Builder(rs.getString("cid")).addName(rs.getString("courseName"))
                        .addStartTime(rs.getTime("startTime").toLocalTime())
                        .addEndTime(rs.getTime("endTime").toLocalTime()).addDay(rs.getInt("day"))
                        .addLocation(rs.getString("location")).build());
            }
            rs.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
        INSTRUCTOR
    */


    // For instructor permission checking
    // Design choice:
    // Each course only has one owner, and this applies to the lab as well. Only the owner of the course
    // is permitted to access/modify data. That said, lectures and labs are separated, and lab grades
    // can only be assigned by the owner of lab - usually TAs. And TAs cannot assign grades for students
    // in lecture b/c owner of lecture is usually a faculty. {Example: lecture = MPCS 100, Lab = MPCS 100A}
    public boolean instrIsPermitted(int instructorID, String courseID, Quarter q, int year) {
        try {
            boolean isPermitted = false;
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CourseTeaching where iid = "
                    + instructorID + " and cid = '" + courseID+ "' and quarter = '" + q + "' and year = " + year);
            if (rs.next()) {
                isPermitted = true;
            }
            rs.close();
            return isPermitted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    // SCHEDULE
    public List<Course> instrCourseTeachingSchedule(int instructorID) {
        try {
            List<Course> lst = new ArrayList<>();
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CourseTeaching as e join Course as c on e.cid=c.cid where iid = "
                    + instructorID + " and quarter = '" + Reference.getQuarter().toString()
                    + "' and year = " + Reference.getYear());
            while (rs.next()) {
                lst.add(new Course.Builder(rs.getString("cid")).addName(rs.getString("courseName"))
                        .addStartTime(rs.getTime("startTime").toLocalTime())
                        .addEndTime(rs.getTime("endTime").toLocalTime()).addDay(rs.getInt("day"))
                        .addLocation(rs.getString("location")).build());
            }
            rs.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    


    // ROSTER
    public List<StudentProfile> instrGetStudentProfilesInClass(String courseID) {
        try {
            List<StudentProfile> profiles = new ArrayList<>();
            Statement myStmt = mySQLConn.createStatement();

            ResultSet rs = myStmt.executeQuery("select s.sid, s.name, m.cNetID, s.department, s.major, s.email" +
                            " from Student s, Member m, Enrollment e where s.sid = m.id and e.sid = s.sid and e.cid" +
                            " = '" + courseID + "' and e.quarter = '"+ Reference.getQuarter() + "' and e.year = " +
                            Reference.getYear() + ";");
            while (rs.next()) {
                profiles.add(new StudentProfile.Builder(rs.getInt("sid")).addName(rs.getString("name"))
                        .addCNetID(rs.getString("cNetID")).addMajor(rs.getString("major"))
                        .addDepartment(rs.getString("department")).addEmail(rs.getString("email"))
                        .build());
            }
            rs.close();
            return profiles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // COURSE EMAIL
    public String instrGetInstructorEmail(int instructorID) {
        try {
            String email = null;
            Statement stmt = mySQLConn.createStatement();
            ResultSet rs = stmt.executeQuery("select email from Instructor where iid = "
                    + instructorID + ";");
            if (rs.next()) {
                email = rs.getString("email");
            }
            rs.close();
            return email;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<String> instrGetStudentEmailInClass(String courseID) {
        try {
            List<String> emails = new ArrayList<>();
            Statement myStmt = mySQLConn.createStatement();

            ResultSet rs = myStmt.executeQuery("select s.email" +
                    " from Student s, Enrollment e where e.sid = s.sid and e.cid" +
                    " = '" + courseID + "';");
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
            rs.close();
            return emails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String instrSendEmail(String fromEmail, List<String> toEmails, String msg) {
        // Stub function since we don't have to implement Email sending.
        String res = "Email successfully sent as following:\n";
        res += "\tFrom: " + fromEmail + "\n";
        res += "\tTo: " + String.join(", ", toEmails) + "\n";
        res += "\tMessage: '" + msg + "'\n";
        return res;
    }


    // GRADE ASSIGNMENT
    // Fetch grades (from MYSQL DB) of students in specified courseID, in the ongoing current academic quarter.
    // Input:
    //  courseID String
    // Output:
    //  TreeMap of student_ID : {name, grade} = Integer : String[].
    public Map<Integer, String[]> instrFetchGrades(String courseID, Quarter q, int year) {
        try {
            Map<Integer, String[]> gradesMap = new TreeMap<>();
            Statement myStmt = mySQLConn.createStatement();
            ResultSet rs = myStmt.executeQuery("select e.sid, name, grade from Enrollment e, Student s " +
                    "where e.sid = s.sid and quarter = '" + q + "' and year = "
                    + year + " " + "and cid = '" + courseID + "';");
            while (rs.next()) {
                gradesMap.put(rs.getInt("sid"), new String[] {rs.getString("name"), rs.getString("grade")});
            }
            rs.close();
            return gradesMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update grades of a class to MYSQL database
    public String instrWriteGrades(String courseID, Map<Integer, String> grades, Quarter q, int year) {
        try {
            Statement myStmt = mySQLConn.createStatement();
            for (int id : grades.keySet()) {
                myStmt.executeUpdate("update Enrollment set grade = '" + grades.get(id) +
                        "' where sid = " + id + " and " + "cid = '" + courseID + "' and quarter = '" + q +
                        "' and year = " + year + ";");
            }
            return "Grades updated for course: " + courseID + "\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred when uploading grades into Database.\n";
        }
    }

    public List<String[]> instrGetCourseTaughtHistory(int instrID) {
        try {
            List<String[]> lst = new ArrayList<>();
            Statement myStmt = mySQLConn.createStatement();
            ResultSet rs = myStmt.executeQuery("select * from CourseTeaching where iid = " + instrID + ";");
            while (rs.next()) {
                lst.add(new String[] {rs.getString("cid"), rs.getString("quarter"), rs.getString("year")});
            }
            rs.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}