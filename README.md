# MyUniversityRegistrar: A Mock-up of Registrar System
**Scope of the project:**
The bounded context chosen is "Registrar service". Main actors are student, faculty, studentTA, non-studentTA. The Registrar handles tasks such as (1.) allowing students to view grade(s), restriction status, transcript, course schedule, as well as (2.) allowing instructors to view current class roster, send emails to all students in a course, add/modify student grades, and view current teaching schedule. Note that system administrator is not part of this scope, as most of its responsibility are related to registration processes, which are not in scope of registrar service. As far as implementation goes, many design patterns and S.O.L.I.D principles are used and followed. In each classes' comment section, short description about what it does as well as what pattern(s) are used are noted. A class UML diagram (named "Class Diagram.pdf") is also provided to give a birdseye view on the infrastructure of the project.


**How to run code:**
I used IntelliJ to create and run this project during the process (a file named FinalProject.iml can be found because of this). I believe other IDEs such as Eclipse could also work as well, but IntelliJ might be the easiest way to run this project.
Development code locates under com/src, in which different branches of service are organized into distinct packages (ex. com/src/gradefetch).
Test cases locate under com/test.
External Libraries include use of mysql-connector-java-8.0.23.jar, mongo-java-driver-3.12.8.jar, and JUnit4 & 5. Remember to link them when running the program. mysql-connector-java-8.0.23.jar and mongo-java-driver-3.12.8.jar are placed in the same directory as src and out folders when I run them.
To run the project, run the main method under App.java (use IntelliJ's run button in App.java - compilation work will be done for you).


**Unit Tests:**
There are 5 test files in total located under com/test. One of them tests the registrar service, while the rest test from each
client classes' perspective (student, faculty, studentTA, non-studentTA).
To run the tests, right click on com/test folder and click run all 5 test cases. These test cases run on different accounts from those provide in Manual Test (for the purpose of data modification, such as updating grades).
Note that some exception/error cases are not covered in unit-testing due to difficulty in duplicating them, but I should have manually tested most/all of them!


**Manual Test:**
5 sets of accounts are given below as test accounts for manual testing. Every account has the same password = '123'.
- 1. CNetID: 'test_student'
- 2. CNetID: 'test_faculty'
- 3. CNetID: 'test_sta'
- 4. CNetID: 'test_nsta'
- 5. CNetID: 'random_stud'
Other account information can be found in MongoDB, but are not recommended for manual testing. The reason being any data modification (updating new grades, etc) can break the unit test cases.
Note that "gradesheet get" will create a txt file in the directory of "Final Project", and that "gradesheet post" will read a file from the same directory.


**How to Setup MySQL DB and MongoDB:**
These are DB configuration constants my program uses. If different constants are used when re-creating the DBs, feel free to change them in Reference.java under com/src.
-MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/registrar";
-MYSQL_USERNAME = "root";
-MYSQL_PASSWORD = "password";
-MONGODB_HOSTNAME = "localhost";
-MONGODB_PORT = 27017;
-MONGODB_DBNAME = "registrar";

MySQL - A file named "sqlData.sql" can be found. To import this, run the script which creates tables and import all data.
MongoDB - the database is named "registrar", in which there is only one collection called "userlogin".
Use the following commands in the mongo shell to inject all data needed.

db.userlogin.insert({"cNetID":"rpchiu", "password":"123", "role":"student", "name":"Ryan", "id" : NumberInt(1)})
db.userlogin.insert({"cNetID":"mark", "password":"123", "role":"faculty", "name":"Mark", "id" : NumberInt(2)})
db.userlogin.insert({"cNetID":"johnoop", "password":"123", "role":"nonStudentTA", "name":"John", "id" : NumberInt(3)})
db.userlogin.insert({"cNetID":"jeffoop", "password":"123", "role":"studentTA", "name":"Jeff", "id" : NumberInt(4)})
db.userlogin.insert({"cNetID":"amyq", "password":"123", "role":"student", "name":"Amy", "id" : NumberInt(6)})
db.userlogin.insert({"cNetID":"bob", "password":"123", "role":"student", "name":"Bob", "id" : NumberInt(7)})
db.userlogin.insert({"cNetID":"maryk", "password":"123", "role":"studentTA", "name":"Mary", "id" : NumberInt(8)})
db.userlogin.insert({"cNetID":"test_student", "password":"123", "role":"student", "id" : NumberInt(10), "name":"Ryan Chiu"})
db.userlogin.insert({"cNetID":"test_faculty", "password":"123", "role":"faculty", "id" : NumberInt(11), "name":"Mark Shacklette"})
db.userlogin.insert({"cNetID":"test_nsta", "password":"123", "role":"nonStudentTA", "id" : NumberInt(12), "name":"John Hadidian-Baugher"})
db.userlogin.insert({"cNetID":"test_sta", "password":"123", "role":"studentTA", "id" : NumberInt(13), "name":"Alan Salkanović"})
db.userlogin.insert({"cNetID":"random_stud", "password":"123", "role":"student", "id" : NumberInt(14), "name":"John Doe"})

After insertion, data should look like the following:
{
	"_id" : ObjectId("6048d750a795c69af0a96733"),
	"cNetID" : "rpchiu",
	"password" : "123",
	"role" : "student",
	"id" : 1,
	"name" : "Ryan"
}
{
	"_id" : ObjectId("6048f95a3bab3e170212c3ca"),
	"cNetID" : "mark",
	"password" : "123",
	"role" : "faculty",
	"name" : "Mark",
	"id" : 2
}
{
	"_id" : ObjectId("6048f9933bab3e170212c3cb"),
	"cNetID" : "johnoop",
	"password" : "123",
	"role" : "nonStudentTA",
	"name" : "John",
	"id" : 3
}
{
	"_id" : ObjectId("6048f9ab3bab3e170212c3cc"),
	"cNetID" : "jeffoop",
	"password" : "123",
	"role" : "studentTA",
	"name" : "Jeff",
	"id" : 4
}
{
	"_id" : ObjectId("6048f9f63bab3e170212c3cd"),
	"cNetID" : "amyq",
	"password" : "123",
	"role" : "student",
	"name" : "Amy",
	"id" : 6
}
{
	"_id" : ObjectId("6048fa093bab3e170212c3ce"),
	"cNetID" : "bob",
	"password" : "123",
	"role" : "student",
	"name" : "Bob",
	"id" : 7
}
{
	"_id" : ObjectId("6048fa1c3bab3e170212c3cf"),
	"cNetID" : "maryk",
	"password" : "123",
	"role" : "studentTA",
	"name" : "Mary",
	"id" : 8
}
{
	"_id" : ObjectId("604b96d294c6cd9448768a82"),
	"cNetID" : "test_student",
	"password" : "123",
	"role" : "student",
	"id" : 10,
	"name" : "Ryan Chiu"
}
{
	"_id" : ObjectId("604b96e694c6cd9448768a83"),
	"cNetID" : "test_faculty",
	"password" : "123",
	"role" : "faculty",
	"id" : 11,
	"name" : "Mark Shacklette"
}
{
	"_id" : ObjectId("604b96f794c6cd9448768a84"),
	"cNetID" : "test_nsta",
	"password" : "123",
	"role" : "nonStudentTA",
	"id" : 12,
	"name" : "John Hadidian-Baugher"
}
{
	"_id" : ObjectId("604b970094c6cd9448768a85"),
	"cNetID" : "test_sta",
	"password" : "123",
	"role" : "studentTA",
	"id" : 13,
	"name" : "Alan Salkanović"
}
{
	"_id" : ObjectId("604baa31c46f8477b26273e2"),
	"cNetID" : "random_stud",
	"password" : "123",
	"role" : "student",
	"id" : 14,
	"name" : "John Doe"
}
