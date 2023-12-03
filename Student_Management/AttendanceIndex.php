<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Attendance</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            margin: 20px;
            background-color: #FFFFFF;
            color: #333;
        }

        h1 {
            color: #2196F3;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #BDBDBD;
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<?php
include 'db.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Handle form submission
    include 'updateAttendance.php';
}
//
// Fetch student attendance data
$sql = "SELECT * FROM studentattendance";
$result = $conn->query($sql);
//
if ($result->num_rows > 0) {//
    //
    echo '<h1>Student Attendance</h1>';
    echo '<form method="post" action="">';
    echo '<table border="1">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Attendance ID</th>';
    echo '<th>Student PIN</th>';
    echo '<th>Attendance Date</th>';
    echo '<th>Attendance Time</th>';
    echo '<th>Is Present</th>';
    echo '<th>Actions</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';

    while ($row = $result->fetch_assoc()) {
        echo '<tr>';
        echo '<td>' . $row['attendance_id'] . '</td>';
        echo '<td>' . $row['student_pin'] . '</td>';
        echo '<td>' . $row['attendance_date'] . '</td>';
        echo '<td>' . $row['attendance_time'] . '</td>';
        echo '<td id="attendanceStatus_' . $row['attendance_id'] . '">' . ($row['is_present'] == 1 ? 'Present' : 'Absent') . '</td>';
        echo '<td>';
        echo '<button type="submit" name="markPresent" value="' . $row['attendance_id'] . '">Mark Present</button>';
        echo '<button type="submit" name="markAbsent" value="' . $row['attendance_id'] . '">Mark Absent</button>';
        echo '</td>';
        echo '</tr>';
    }

    echo '</tbody>';
    echo '</table>';
    echo '</form>';
} else {
    echo 'No attendance data found';
}

// Close the database connection
// $conn->close();
?>

<script>
    function markPresent(attendanceId) {
        updateAttendance(attendanceId, true);
    }

    function markAbsent(attendanceId) {
        updateAttendance(attendanceId, false);
    }

    function updateAttendance(attendanceId, isPresent) {
        // Send AJAX request to update the database
        const formData = new FormData();
        formData.append('attendanceId', attendanceId);
        formData.append('isPresent', isPresent);

        fetch('updateAttendance.php', {
            method: 'POST',
            body: formData,
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Read the response as text
        })
        .then(data => {
            // Handle the response data as needed
            if (data === 'success') {
                alert('Attendance updated successfully!');
                // Update the attendance status in the table on the main page
                updateAttendanceStatus(attendanceId, isPresent);
            } else {
                console.error('Error updating attendance:', data);
                // Handle the case where the update was not successful
                // You might want to display an error message to the user
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            // Handle errors, display an error message, etc.
        });
    }

    function updateAttendanceStatus(attendanceId, isPresent) {
        // Find the cell in the table and update its content
        const cell = document.getElementById(`attendanceStatus_${attendanceId}`);
        if (cell) {
            // Update the content
            cell.textContent = isPresent ? 'Present' : 'Absent';
            // Optionally, you can change the cell style or do other manipulations
            cell.style.color = isPresent ? 'green' : 'red';
        }
    }
</script>
</body>
</html>
