<?php
include 'db.php';

$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Retrieve form data
    $pin = $_POST['pin'];
    $name = $_POST['name'];
    $email = $_POST['email'];
    $uid = $_POST['uid'];
    $address = $_POST['address'];

    // Update data in the database
    $sql = "UPDATE students SET student_name = '$name', email = '$email', uid = '$uid', address = '$address' WHERE pin = '$pin'";

    if ($conn->query($sql) === TRUE) {
        $response['success'] = true;
        $response['message'] = 'Student details updated successfully';
    } else {
        $response['success'] = false;
        $response['message'] = 'Error updating student details: ' . $conn->error; // Fix here
    }
} else {
    $response['success'] = false;
    $response['message'] = 'Invalid request method';
}

// Send a JSON response
header('Content-Type: application/json');
echo json_encode($response);

$conn->close();
?>
