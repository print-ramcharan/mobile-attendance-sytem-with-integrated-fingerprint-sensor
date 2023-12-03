<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Data</title>
    <style>
       /* Add your CSS styles here */

body {
    font-family: 'Arial', sans-serif;
    text-align: center;
    margin: 20px;
    background-color: #FFFFFF; /* White background */
    color: #333; /* Dark color for text */
}

h1 {
    color: #2196F3; /* Blue color for headings */
}

table {
    width: 80%;
    margin: 20px auto;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #BDBDBD; /* Light gray color for borders */
    padding: 10px;
    text-align: left;
}

/* Modal styles */
.modal {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.7);
    justify-content: center;
    align-items: center;
}

.modal-content {
    background-color: #FFF; /* White color for modal content */
    padding: 20px;
    border-radius: 10px;
    text-align: left;
}

.close {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 20px;
    cursor: pointer;
    color: #333;
}

/* Add more styles as needed */

/* Style for circular images */
table img {
    border-radius: 50%;
    max-width: 40px; /* Adjust the max-width as needed */
}

/* Button style */
button {
    background-color: #4CAF50; /* Green color for buttons */
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

button:hover {
    background-color: #45a049; /* Darker green color on hover */
}
/* Add your CSS styles here */

/* Modal content styles */
.modal-content {
    background-color: #FFF; /* White color for modal content */
    padding: 20px;
    border-radius: 10px;
    text-align: left;
    max-width: 400px; /* Adjust the max-width as needed */
    margin: 0 auto; /* Center the modal content horizontally */
}

/* Close button styles */
.close {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 20px;
    cursor: pointer;
    color: #333;
}

/* Form styles */
form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form-group {
    margin-bottom: 15px;
    width: 100%;
}

/* Label styles */
label {
    margin-bottom: 5px;
    font-weight: bold;
}

/* Input styles */
input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #BDBDBD; /* Light gray color for borders */
    border-radius: 5px;
    margin-top: 5px;
}

/* Button styles */
button {
    background-color: #4CAF50; /* Green color for buttons */
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

button:hover {
    background-color: #45a049; /* Darker green color on hover */
}

/* Add more styles as needed */

/* Add more styles as needed */

        /* Add more styles as needed */
    </style>
</head>
<body>

<?php
include 'db.php';

// Fetch students data
$sql = "SELECT * FROM students";
$result = $conn->query($sql);

$students = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $students[] = $row;
    }
}

// Close the database connection
$conn->close();
?>

<h1>Student Management</h1>

<!-- Button to add a new student -->
<button onclick="openAddModal()">Add New Student</button>

<!-- Table to display existing students -->
<table border="1">
    <thead>
        <tr>
            <th>Photo</th>
            <th>PIN</th>
            <th>Name</th>
            <th>Email</th>
            <th>UID</th>
            <th>Address</th>
            <th>Actions</th>
            <!-- Add more columns as needed -->
        </tr>
    </thead>
    <tbody>
        <?php foreach ($students as $student) : ?>
            <tr>
                <td><img src="<?= $student['photo'] ?>" alt="Student Photo"></td>
                <td><?= $student['pin'] ?></td>
                <td><?= $student['student_name'] ?></td>
                <td><?= $student['email'] ?></td>
                <td><?= $student['uid'] ?></td>
                <td><?= $student['address'] ?></td>
                <td>
                    <button onclick="openEditModal('<?= $student['pin'] ?>', '<?= $student['student_name'] ?>', '<?= $student['email'] ?>', '<?= $student['uid'] ?>', '<?= $student['address'] ?>')">Edit</button>
                </td>
                <!-- Add more columns as needed -->
            </tr>
        <?php endforeach; ?>
    </tbody>
</table>

<!-- Modal for adding a new student -->
<div id="addModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeAddModal()">&times;</span>
        <h2>Add New Student</h2>
        <form id="addForm" action="addStudent.php" method="post" enctype="multipart/form-data">
            <!-- Add form fields for new student here -->
            <label for="pin">PIN:</label>
            <input type="text" id="pin" name="pin" required>

            <label for="studentName">Student Name:</label>
            <input type="text" id="studentName" name="studentName" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="isRegistered">Is Registered:</label>
            <input type="checkbox" id="isRegistered" name="isRegistered">

            <label for="uid">UID:</label>
            <input type="text" id="uid" name="uid" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address">

            <label for="photo">Photo:</label>
            <input type="file" id="photo" name="photo">

            <button type="submit">Add Student</button>
        </form>
    </div>
</div>

<!-- Modal for editing an existing student -->
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditModal()">&times;</span>
        <h2>Edit Student Details</h2>
        <form id="editForm" action="updateStudent.php" method="post">
            <!-- Add form fields for editing student here -->
            <div class="form-group">
                <label for="editPin">PIN:</label>
                <input type="text" id="editPin" name="editPin" readonly>
            </div>
            <div class="form-group">
                <label for="editName">Name:</label>
                <input type="text" id="editName" name="name" required>
            </div>
            <div class="form-group">
                <label for="editEmail">Email:</label>
                <input type="text" id="editEmail" name="email" required>
            </div>
            <div class="form-group">
                <label for="editUID">UID:</label>
                <input type="text" id="editUID" name="uid" required>
            </div>
            <div class="form-group">
                <label for="editAddress">Address:</label>
                <input type="text" id="editAddress" name="address">
            </div>
            <button type="button" onclick="saveChanges()">Save Changes</button>
        </form>
    </div>
</div>

<div id="loadingSpinner" class="spinner"></div>

<!-- Include this JavaScript code in your HTML file -->
<!-- Include this JavaScript code in your HTML file -->
<script>
    // Function to open the modal for adding a new student
    function openAddModal() {
        document.getElementById('addModal').style.display = 'block';
    }

    // Function to close the modal for adding a new student
    function closeAddModal() {
        document.getElementById('addModal').style.display = 'none';
    }

    // Function to open the modal for editing an existing student
    function openEditModal(pin, name, email, uid, address) {
        document.getElementById('editPin').value = pin;
        document.getElementById('editName').value = name;
        document.getElementById('editEmail').value = email;
        document.getElementById('editUID').value = uid;
        document.getElementById('editAddress').value = address;

        document.getElementById('editModal').style.display = 'block';
    }

    // Function to close the modal for editing an existing student
    function closeEditModal() {
        document.getElementById('editModal').style.display = 'none';
    }

    // Function to submit form data to a PHP function
    // Function to submit form data to update student details
// Function to submit form data to a PHP function
// Function to submit form data to a PHP function
// Function to submit form data to a PHP function
function saveChanges() {
    // Get other form field values
    const pin = document.getElementById('editPin').value;
    const name = document.getElementById('editName').value;
    const email = document.getElementById('editEmail').value;
    const uid = document.getElementById('editUID').value;
    const address = document.getElementById('editAddress').value;

    // Create a FormData object to send both text and file data
    const formData = new FormData();
    formData.append('pin', pin);
    formData.append('name', name);
    formData.append('email', email);
    formData.append('uid', uid);
    formData.append('address', address);

    // Send the data to the server using Fetch API
    fetch('updateStudent.php', {
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
            console.log('Data received successfully:', data);

            // Check if the update was successful
            if (data.includes('success')) {
                // You might want to update the UI or display the data in some way
                closeEditModal();
                updateStudentTable();
                // updateTableRows();
            } else {
                // Handle the case where the update was not successful
                console.error('Error updating student details:', data);
                // You might want to display an error message to the user
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            // Handle errors, display an error message, etc.
        });
}



    // Function to update the student table on the main page
    function updateStudentTable() {
    // Fetch updated student data from the server
    fetch('getStudents.php')
        .then(response => response.text()) // Read the response as text
        .then(htmlContent => {
            // Get the reference to the table body
            const tbody = document.querySelector('table tbody');
            
            // Replace the content of the existing table body with the new data
            tbody.innerHTML = htmlContent;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            // Handle errors, display an error message, etc.
        });
}



    // Function to update table rows with new student data
    function updateTableRows(students) {
        // Select the table body
        const tbody = document.querySelector('table tbody');

        // Clear existing rows
        tbody.innerHTML = '';

        // Iterate through the students and add rows to the table
        students.forEach(student => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td><img src="${student.photo}" alt="Student Photo"></td>
                <td>${student.pin}</td>
                <td>${student.student_name}</td>
                <td>${student.email}</td>
                <td>${student.uid}</td>
                <td>${student.address}</td>
                <td>
                    <button onclick="openEditModal('${student.pin}', '${student.student_name}', '${student.email}', '${student.uid}', '${student.address}')">Edit</button>
                </td>
            `;
            tbody.appendChild(newRow);
        });
    }
</script>

</body>
</html>
