<?php
include 'db.php';

$sql = "SELECT * FROM students";
$result = $conn->query($sql);

$students = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $students[] = $row;
    }
}

$conn->close();
?>

<!-- Table body content -->
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
    </tr>
<?php endforeach; ?>
