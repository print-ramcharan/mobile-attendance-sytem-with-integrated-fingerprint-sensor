<?php
$hostname = "localhost";
$username = "root";
$password = "3223";
$dbname = "voldemort";
try {
    $conn = new mysqli('', 'root', '3223', 'voldemort');
    // Your code here
} catch (mysqli_sql_exception $e) {
    echo 'Connection failed: ' . $e->getMessage();
}

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
else{
    // echo "success ram is success";
}
// CRUD operations go here...

// Close the database connection
// $mysqli->close();
?>
