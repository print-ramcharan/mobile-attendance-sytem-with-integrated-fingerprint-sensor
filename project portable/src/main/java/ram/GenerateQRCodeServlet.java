package ram;



import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@WebServlet("/GenerateQRCodeServlet")
public class GenerateQRCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");

        if ("wifi".equals(type)) {
            generateAndSendWifiQRCode("vivo 1901", "1234567890", response);
        } else if ("attendance".equals(type)) {
            generateAndSendAttendanceQRCode("http://192.168.0.152:8080/project_portable/markAttendance", response);
        }
    }

    private void generateAndSendWifiQRCode(String ssid, String password, HttpServletResponse response) throws IOException {
        String wifiData = "WIFI:T:WPA;S:" + ssid + ";P:" + password + ";;";
        generateAndSendQRCode(wifiData, response);
    }

    private void generateAndSendAttendanceQRCode(String url, HttpServletResponse response) throws IOException {
        generateAndSendQRCode(url, response);
    }

    private void generateAndSendQRCode(String data, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            BitMatrix bitMatrix = generateQRCodeMatrix(data);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private BitMatrix generateQRCodeMatrix(String data) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200,
                java.util.Collections.singletonMap(EncodeHintType.MARGIN, 2));
    }
}
