package ram;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/studentLogin")
public class StudentLoginServlet extends HttpServlet {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/voldemort";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3223";
    private static final int TOTAL_DAYS = 100;  // Change this value as needed

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Parse student email and password from request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String uid = request.getParameter("UID");
        String message = request.getParameter("message");

        // Check the value of the "message" parameter
        if (message != null && message.equals("store")) {
            // If the "message" is "store," store the UID in the table
            boolean stored = storeUID(email, uid);
            if (stored) {
            	  String studentPin = authenticateStudent(email, password);

                  if (studentPin != null) {
                      // Retrieve student's attendance data and calculate attendance percentage
                      JSONObject responseData = getStudentAttendanceData(studentPin);

                      // Set the response content type to JSON
                      response.setContentType("application/json");

                      // Write the student details as JSON to the response
                      response.getWriter().write(responseData.toString());
                  } else {
                      // Handle authentication failure
                      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                      response.getWriter().write("Authentication failed");
                  }
                  System.out.println("UID stored successfully");
            } else {
            	System.out.println("Failed to store UID");
                
            }
        } else if(message.equals("check")){
        	 
            // Authenticate the student and get student PIN
            String studentPin = authenticateStudentwithUid(email, password,uid);

            if (studentPin != null) {
                // Retrieve student's attendance data and calculate attendance percentage
                JSONObject responseData = getStudentAttendanceData(studentPin);

                // Set the response content type to JSON
                response.setContentType("application/json");

                // Write the student details as JSON to the response
                response.getWriter().write(responseData.toString());
            } else {
                // Handle authentication failure
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Authentication failed");
            }
        }else {
        	response.setContentType("application/json");
        	response.getWriter().write("Login to your account big guy");
        }
    }
    private boolean storeUID(String email, String uid) {
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Check if the student exists and their email matches
            String emailCheckQuery = "SELECT pin FROM Students WHERE email = ?";
            PreparedStatement emailCheckStatement = connection.prepareStatement(emailCheckQuery);
            emailCheckStatement.setString(1, email);

            ResultSet emailCheckResult = emailCheckStatement.executeQuery();

            if (emailCheckResult.next()) {
                // If the email matches, update the UID
                String updateUIDQuery = "UPDATE Students SET UID = ? WHERE email = ?";
                PreparedStatement updateUIDStatement = connection.prepareStatement(updateUIDQuery);
                updateUIDStatement.setString(1, uid);
                updateUIDStatement.setString(2, email);

                int rowsAffected = updateUIDStatement.executeUpdate();
                updateUIDStatement.close();
                connection.close();

                return rowsAffected > 0;
            }

            emailCheckResult.close();
            emailCheckStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    private String authenticateStudent(String email, String password) {
        String studentPin = null;

        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement to authenticate the student
            String query = "SELECT pin FROM Students WHERE email = ? AND password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                studentPin = resultSet.getString("pin");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentPin;
    }
    private String authenticateStudentwithUid(String email, String password, String uid) {
        String studentPin = null;

        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement to authenticate the student
            String query = "SELECT pin FROM Students WHERE email = ? AND password = ? AND uid = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, uid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                studentPin = resultSet.getString("pin");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentPin;
    }
    private JSONObject getStudentAttendanceData(String studentPin) {
        JSONObject studentData = new JSONObject();

        int presentDays = 0;
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Retrieve student details from the Students table
            String studentDetailsQuery = "SELECT * FROM Students WHERE pin = ?";
            PreparedStatement studentDetailsStatement = connection.prepareStatement(studentDetailsQuery);
            studentDetailsStatement.setString(1, studentPin);

            ResultSet studentDetailsResultSet = studentDetailsStatement.executeQuery();

            if (studentDetailsResultSet.next()) {
                studentData.put("student_pin", studentDetailsResultSet.getString("pin"));
                studentData.put("student_name", studentDetailsResultSet.getString("student_name"));
                studentData.put("email", studentDetailsResultSet.getString("email"));
                studentData.put("password", studentDetailsResultSet.getString("password"));
            }

            studentDetailsResultSet.close();
            studentDetailsStatement.close();

            // Retrieve student attendance data from the StudentAttendance table
            String attendanceDataQuery = "SELECT attendance_date, attendance_time, is_present FROM StudentAttendance WHERE student_pin = ?";
            PreparedStatement attendanceDataStatement = connection.prepareStatement(attendanceDataQuery);
            attendanceDataStatement.setString(1, studentPin);

            ResultSet attendanceDataResultSet = attendanceDataStatement.executeQuery();

            JSONArray attendanceArray = new JSONArray();

            while (attendanceDataResultSet.next()) {
                JSONObject attendanceObject = new JSONObject();
                attendanceObject.put("attendance_date", attendanceDataResultSet.getString("attendance_date"));
                attendanceObject.put("attendance_time", attendanceDataResultSet.getString("attendance_time"));
                attendanceObject.put("is_present", attendanceDataResultSet.getBoolean("is_present"));

                attendanceArray.put(attendanceObject);

                if (attendanceDataResultSet.getBoolean("is_present")) {
                    presentDays++;
                }
            }

            attendanceDataResultSet.close();
            attendanceDataStatement.close();
            connection.close();

            studentData.put("attendance_data", attendanceArray);

            // Calculate the attendance percentage
            double attendancePercentage = (double) presentDays / TOTAL_DAYS * 100;
            studentData.put("attendance_percentage", attendancePercentage);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentData;
    }
}
