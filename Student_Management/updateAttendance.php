<?php
include 'db.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Retrieve form data
    if (isset($_POST['markPresent'])) {
        $attendanceId = $_POST['markPresent'];
        $isPresent = 1;
    } elseif (isset($_POST['markAbsent'])) {
        $attendanceId = $_POST['markAbsent'];
        $isPresent = 0;
    }

    // Update data in the database
    $sql = "UPDATE studentattendance SET is_present = '$isPresent' WHERE attendance_id = '$attendanceId'";

    if ($conn->query($sql) === TRUE) {
        // Return the updated attendance status
        $updatedStatus = ($isPresent == 1) ? 'Present' : 'Absent';
        echo $updatedStatus;
    } else {
        echo 'Error updating attendance: ' . $conn->error;
    }
}

// $conn->close();
?>
