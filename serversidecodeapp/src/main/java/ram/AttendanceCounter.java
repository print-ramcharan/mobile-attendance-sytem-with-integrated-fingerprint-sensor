package ram;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/AttendanceCounter") // Define your servlet mapping
public class AttendanceCounter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:mysql://localhost:3306/voldemort";
        String username = "root";
        String password = "3223";

        // Create a JSON object to hold the response
        JSONObject jsonResponse = new JSONObject();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Get today's date in the format used in the database (assuming yyyy-MM-dd)
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayFormatted = today.format(dateFormatter);

            // Prepare and execute the SQL query
            String sql = "SELECT COUNT(*) AS present_count FROM studentattendance WHERE attendance_date = ? AND is_present = 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, todayFormatted);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int presentCount = resultSet.getInt("present_count");
                        jsonResponse.put("presentCount", presentCount); // Add attendance count to the response
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the response content type to JSON
        response.setContentType("application/json");

        // Write the JSON response to the client
        response.getWriter().write(jsonResponse.toString());
    }
}
