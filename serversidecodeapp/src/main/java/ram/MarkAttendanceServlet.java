package ram;


import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import org.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@WebServlet("/markAttendance")
public class MarkAttendanceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/voldemort";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3223";
    private boolean isResponseSent = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentPin = request.getParameter("student_pin");

        if (studentPin == null || studentPin.isEmpty()) {
            sendJsonResponse(response, "error", "Invalid request.");
            return;
        }

        if (!isValidStudentPin(studentPin)) {
            sendJsonResponse(response, "error", "Invalid student PIN.");
            return;
        }

        synchronized (studentPin.intern()) {
            if (markStudentAttendance(studentPin)) {
//                generateAndSendWifiQRCode("vivo 1901", "1234567890", response);
//                generateAndSendAttendanceQRCode("http://192.168.0.152:8080/project_portable/markAttendance", response);
                sendJsonResponse(response, "success", "Attendance marked successfully.");
            } else {
                sendJsonResponse(response, "success", "Attendance is already marked.");
            }
        }
    }

    private void sendJsonResponse(HttpServletResponse response, String status, String message) throws IOException {
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", status);
        jsonResponse.put("message", message);
        response.getWriter().write(jsonResponse.toString());
    }

    private boolean isValidStudentPin(String studentPin) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT pin FROM Students WHERE pin = ?")) {

            preparedStatement.setString(1, studentPin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean markStudentAttendance(String studentPin) {
        if (isResponseSent) {
            return false;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String attendanceId = generateUniqueAttendanceId(studentPin);

            String checkQuery = "SELECT student_pin FROM StudentAttendance WHERE student_pin = ? AND is_present = 1 AND attendance_date = CURDATE()";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, studentPin);

                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Attendance already marked");
                        return false;
                    } else {
                        String insertQuery = "INSERT INTO StudentAttendance (attendance_id, student_pin, is_present, attendance_date, attendance_time) VALUES (?, ?, 1, CURDATE(), CURTIME())";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setString(1, attendanceId);
                            insertStatement.setString(2, studentPin);
                            int rowsAffected = insertStatement.executeUpdate();

                            if (rowsAffected == 1) {
                                System.out.println("Attendance marked now");
                                isResponseSent = true;
                                return true;
                            } else {
                                System.out.println("Failed to mark attendance");
                                isResponseSent = true;
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Attendance not marked");
        }
        return false;
    }

    private String generateUniqueAttendanceId(String studentPin) {
        UUID uuid = UUID.randomUUID();
        String timestamp = Long.toString(System.currentTimeMillis());
        String data = studentPin + uuid + timestamp;
        return sha256(data);
    }

    private String sha256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

//    private BitMatrix generateQRCodeMatrix(String data) throws WriterException {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        return qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200,
//                java.util.Collections.singletonMap(EncodeHintType.MARGIN, 2));
//    }

//    private void generateAndSendWifiQRCode(String ssid, String password, HttpServletResponse response) throws IOException {
//        String wifiData = "WIFI:T:WPA;S:" + ssid + ";P:" + password + ";;";
//        generateAndSendQRCode(wifiData, response);
//    }
//
//    private void generateAndSendAttendanceQRCode(String url, HttpServletResponse response) throws IOException {
//        generateAndSendQRCode(url,response);
//    }

//    private void generateAndSendQRCode(String data, HttpServletResponse response) throws IOException {
//        try {
//            response.setContentType("image/png");
//            ServletOutputStream outputStream = response.getOutputStream();
//            BitMatrix bitMatrix = generateQRCodeMatrix(data);
//            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
//            outputStream.flush();
//            outputStream.close();
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }
}
