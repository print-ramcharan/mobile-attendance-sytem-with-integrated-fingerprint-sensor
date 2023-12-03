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

@WebServlet("/AttendancePercentageServlet")
public class AttendancePercentageServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/voldemort";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3223"; // Replace with your actual DB password
    private static final int TOTAL_DAYS = 100; // Change this as needed

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentPIN = request.getParameter("pin");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Prepare and execute the SQL query to count the days the student is present
            String presentDaysSQL = "SELECT COUNT(*) AS present_count FROM studentattendance WHERE student_pin = ? AND is_present = 1";
            int presentDays = 0;

            try (PreparedStatement presentDaysStatement = connection.prepareStatement(presentDaysSQL)) {
                presentDaysStatement.setString(1, studentPIN);
                try (ResultSet presentDaysResultSet = presentDaysStatement.executeQuery()) {
                    if (presentDaysResultSet.next()) {
                        presentDays = presentDaysResultSet.getInt("present_count");
                    }
                }
            }

            // Calculate the attendance percentage
            double attendancePercentage = (double) presentDays / TOTAL_DAYS * 100;

            // Send the attendance percentage and total days as a response
            response.setContentType("text/plain");
            response.getWriter().write("Student PIN: " + studentPIN + "\n");
            response.getWriter().write("Total Days: " + TOTAL_DAYS + "\n");
            response.getWriter().write("Present Days: " + presentDays + "\n");
            response.getWriter().write("Attendance Percentage: " + attendancePercentage + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
