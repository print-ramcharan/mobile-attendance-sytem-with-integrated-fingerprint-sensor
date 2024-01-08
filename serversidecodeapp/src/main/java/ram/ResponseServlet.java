package ram;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
    // This method handles the POST request sent from your Android app
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check email and password in the database
        if (isLoginValid(email, password)) {
            // Create a JSON response for a successful login
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", "Success");
            jsonResponse.put("studentName", "John Doe"); // Replace with actual data

            // Set the content type to JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
        } else {
            // Create a JSON response for a failed login
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("result", "Failure");

            // Set the content type to JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
        }
    }

    // Implement a method to check the email and password in the database
    private boolean isLoginValid(String email, String password) {
        // Check the database and return true if valid, else return false
        // Replace this with your actual database logic
        return email.equals("hello@gmail.com") && password.equals("3223");
    }
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//    // Database credentials
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/ram";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "3223";
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Get user input from the request
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // Initialize the database connection
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        // Response writer
//        PrintWriter out = response.getWriter();
//
//        try {
//            // Register JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Open a connection
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            // SQL query to check if user exists
//            String sql = "SELECT * FROM credential WHERE email = ? AND password = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            ResultSet resultSet = stmt.executeQuery();
//
//            if (resultSet.next()) {
//                // User exists, you can send data back to the client
//                String userData = resultSet.getString("email");
//                out.println("Login successful. User data: " + userData);
//            } else {
//                // User doesn't exist
//                out.println("Login failed. Invalid username or password.");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            // Handle database errors
//            e.printStackTrace();
//            out.println("An error occurred.");
//        } finally {
//            // Close resources
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
