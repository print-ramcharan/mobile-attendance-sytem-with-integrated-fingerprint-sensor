<?php
include 'db.php';

// Fetch student attendance data
$sql = "SELECT * FROM studentattendance";
$result = $conn->query($sql);

$attendanceData = [];

if ($result->num_rows > 0) {
    echo '<table border="1">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Attendance ID</th>';
    echo '<th>Student PIN</th>';
    echo '<th>Attendance Date</th>';
    echo '<th>Attendance Time</th>';
    echo '<th>Is Present</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';
    
    while ($row = $result->fetch_assoc()) {
        echo '<tr>';
        echo '<td>' . $row['attendance_id'] . '</td>';
        echo '<td>' . $row['student_pin'] . '</td>';
        echo '<td>' . $row['attendance_date'] . '</td>';
        echo '<td>' . $row['attendance_time'] . '</td>';
        echo '<td>' . $row['is_present'] . '</td>';
        echo '</tr>';
    }

    echo '</tbody>';
    echo '</table>';
} else {
    echo 'No attendance data found';
}

// Close the database connection
$conn->close();
?>
