<?php
include 'db.php';

$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Retrieve form data
    $pin = $_POST['pin'];
    $studentName = $_POST['studentName'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $isRegistered = isset($_POST['isRegistered']) ? 1 : 0;
    $uid = $_POST['uid'];
    $address = $_POST['address'];

    // Handle file upload (photo)
    $photo = $_FILES['photo']['name'];
    $photo_temp = $_FILES['photo']['tmp_name'];
    
    $upload_directory = 'uploads/';
    if (!file_exists($upload_directory)) {
        mkdir($upload_directory, 0777, true);
    }

    $photo_path = $upload_directory . $photo;

    move_uploaded_file($photo_temp, $photo_path);

    // Insert data into the database
    $sql = "INSERT INTO students (pin, student_name, email, password, is_registered, uid, address, photo) 
            VALUES ('$pin', '$studentName', '$email', '$password', $isRegistered, '$uid', '$address', '$photo_path')";

    // Use a new mysqli connection for fetching data
    try {
        $conn_fetch = new mysqli('localhost', 'root', '3223', 'voldemort');
        // Your code here
    } catch (mysqli_sql_exception $e) {
        echo 'Connection failed: ' . $e->getMessage();
    }
    
    if ($conn_fetch->connect_error) {
        die("Connection failed: " . $conn_fetch->connect_error);
    }

    // Fetch updated student data
    $sql_fetch = "SELECT * FROM students";
    $result_fetch = $conn_fetch->query($sql_fetch);

    $students = [];

    if ($result_fetch->num_rows > 0) {
        while ($row = $result_fetch->fetch_assoc()) {
            $students[] = $row;
        }
    }

    // Close the new mysqli connection used for fetching data
    $conn_fetch->close();

    if ($conn->query($sql) === TRUE) {
        $response = ['success' => true, 'message' => 'Student added successfully', 'students' => $students];
    } else {
        $response = ['success' => false, 'message' => 'Error adding student: ' . $conn->error];
    }
}

// Close the original mysqli connection
$conn->close();

// Return the response in JSON format
echo json_encode($response);
?>
