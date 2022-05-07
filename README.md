# University-Management-System-with-OOAD

A University / College Management System that allows you to log in as the admin, a student or staff member and view all relevant information about the University related to the particular user.
Design Patterns Used - Singleton for Admin and Database, Facade for Home Page for each user
Architecture Used - Model View Controller (MVC)
Design Principles Used - Single Responsibility Principle (SRP), Open-Closed Principle (OCP)

Key Features:
* Users can register as a student, a staff member, or an admin
* Students are able to enroll in courses exclusive to their respective college, view the courses they enrolled in, and view & change their personal information
* All staff members can view and change their personal info, but only instructors are allowed to register to teach a course, view the courses they're teaching, and view the collegiate info of all the students in their courses
* Admins can insert, update, and delete the info of students, staff members, admins, courses, colleges, departments, degrees, & faculty members


# **Installation**
* [Java JDK 12.0.1 and Apache NetBeans 12.0](#java-jdk-12.0.1-and-apache-netbeans-12.0)
* [MySQL Download and Setup](#mysql-download-and-setup)
* [Connecting NetBeans to the MySQL Database](#connecting-netbeans-to-the-mysql-database)
  * [Importing My NetBeans Project](#importing-my-netbeans-project)
  * [Importing My MySQL Schema](#importing-my-mysql-schema)
  * [Connecting the NetBeans Project to the MySQL Schema](#connecting-the-netbeans-project-to-the-mysql-schema)<br></br>

# **Java JDK 12.0.1 and Apache NetBeans 12.0**
1. Download and install [Java JDK 12.0.1](https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html)
2. Download and install [Apache NetBeans 12.0](https://netbeans.apache.org/download/nb120/nb120.html)<br></br>

# **MySQL Download and Setup**
1. Download and install [MySQL Installer 8.0.22](https://dev.mysql.com/downloads/windows/installer/8.0.html)
2. PLEASE [watch this video](https://www.youtube.com/watch?v=2Qi3b_Qu4Xo) while installing MySQL because doing so can easily get confusing. The following screenshot is what I currently have installed. Make sure you at least have everything except MySQL Notifier, MySQL for Excel, Connector/C++, and Connector/NET (you can download them later if desired).<br></br>

3. Open MySQL Workbench & click the plus icon next to MySQL Connections to create a new connection. If you want, change the connection name, username, and password to whatever you prefer, but always remember you can modify them by right-clicking the connection -> Edit Connection.<br></br>


# **Connecting NetBeans to the MySQL Database**

## **Importing My NetBeans Project**
1. Download [my NetBeans project zip file](https://github.com/Paul-Nixon/college_management_system/blob/main/college_management_system_mysql_workbench_export.zip)
2. Open NetBeans -> File -> Import Project -> From ZIP -> Browse button next to the ZIP File text field -> Search for and click the downloaded ZIP file -> Import. Note that the Folder text field shows where the project will be saved.<br></br>

## **Importing My MySQL Schema**

1. Download [my MySQL schema](https://github.com/Paul-Nixon/college_management_system/blob/main/college_management_system_mysql_workbench_export.zip)

2. Open MySQL Workbench -> The new connection you created earlier -> Startup / Shutdown -> Start Server -> Refresh Status (you may or may not need to click the button more than once to ensure a connection has been estalished; The Information box in the bottom left corner should display the connection details)<br></br>  

3. Schemas (label next to Administration) -> Right-click -> Create Schema -> Change the name (I recommend college_management_system) -> Change the charset to "ascii" -> Apply (click it again when the Apply SQL Script to Database window appears) -> Finish<br></br>  

4. Administration -> Data Import/Restore -> Import from Self-Contained File -> Click the button next to the text field, search for the downloaded schema, and click one of the SQL/dump files -> Click the Default Target Schema drop-down box and select the schema you just created -> Import Progress -> Start Import -> Import the remaining files. The schema will now include each of the tables from their respective files.<br></br>

## **Connecting the NetBeans Project to the MySQL Schema**  
1. Open the NetBeans project (if you haven't already) -> Scroll down the Projects tab -> Right-click Libraries -> Add JAR/Folder. On Windows, the path to the MySQL Connector/J JAR file is the following: Windows (C:) -> Program Files (x86) -> MySQL -> Connector J 8.0 (if you know the respective paths on Mac and Linux, post them as an [issue](https://github.com/Paul-Nixon/college_management_system/issues). When you find the file, click it -> Open. The driver enables the project to connect to the database server.<br></br>


2. Services -> Right-click Databases -> Register MySQL Server. In the Basic Properties tab, ensure the server hostname and port number & admin username and password are the same as the MySQL connection you created earlier (if you're confused, on the MySQL Workbench home screen, the admin username is just below the connection name; the server hostname is left of the colon; the server port number is right of the colon; if you forgot the password, remember you can change it by right-clicking the connection -> Edit Connection).<br></br>


3. Click the Admin Properties tab. Click the Browse button next to the Path/URL to admin tool text field, follow the following path: Windows (C:) -> Program Files -> MySQL -> MySQL Server 8.0 -> bin, and click the "mysqladmin" app. Click the Browse button next to the Path to start command text field, follow the previous path, and click the "mysqld" app. Click the Browse button next to the Path to stop command text field, click the "mysqladmin" app again, and, in the Arguments field, type -u root stop. When finished, the Admin Properties tab should resemble the following figure. Click OK when you're satisfied.<br></br>


4. Left-click Databases -> Right-click the MySQL Server node -> Start -> Enter the password to the MySQL connection -> OK. The server is now connected to the project. Note that the server needs to be running for this to work, and you will need to repeat this step every time you open NetBeans.<br></br>


5. Projects -> databaseConnection.java. In the try block, the second statement needs to be slightly modified so the JFrame forms can access and modify the schema you created earlier in your MySQL connection. Here's how it should be changed:
```java
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/*insert schema name here*", "*insert admin username here*", "*insert admin password here*")
```
When you run the project, JFrame forms that need to access the schema will now be able to do so.
