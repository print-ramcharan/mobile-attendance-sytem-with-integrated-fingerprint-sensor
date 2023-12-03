package ram;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/voldemort";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3223";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");

        if (newPassword != null && !newPassword.isEmpty()) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String sql = "UPDATE students SET password = ? WHERE email = (SELECT email FROM password_resets WHERE token = ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, newPassword);
                statement.setString(2, token);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    // Password updated successfully, you can remove the reset token
                    String deleteTokenSql = "DELETE FROM password_resets WHERE token = ?";
                    PreparedStatement deleteTokenStatement = connection.prepareStatement(deleteTokenSql);
                    deleteTokenStatement.setString(1, token);
                    deleteTokenStatement.executeUpdate();

                    // Redirect to a password reset success page
                    response.sendRedirect("password-reset-success.jsp");
                } else {
                    // Handle the case where the token is not found or the update fails
                    response.sendRedirect("password-reset-error.jsp");
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions
            }
        } else {
            // Handle the case where newPassword is null or empty
            response.sendRedirect("password-reset-error.jsp");
        }
    }
}
