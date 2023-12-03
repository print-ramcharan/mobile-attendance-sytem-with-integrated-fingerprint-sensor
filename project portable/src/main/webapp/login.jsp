<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="stylelogin.css">
    <script src="https://kit.fontawesome.com/afc3acaa52.js" crossorigin="anonymous"></script>
    <script src="indexLogin.js"></script>

</head>
<body>
    <div class="cont" >
    <div class="menu-icon" onclick="toggleNav()">
        <div class="bar"></div>
        <div class="bar"></div>
        <div class="bar"></div>
     </div>
   
     <nav class="navigation-pane" id="navPane">
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="http://localhost/Student_Management/index.php">Manage</a></li>
            <li><a href="http://localhost/Student_Management/AttendanceIndex.php">Attendance</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
     </nav>
     
 
    </div>
    <div class="content">
        <div class="login-text">
            <h1>Login</h1>
        </div>
        <div class="boxes">
        <form action="Javaservlet" method="post" class="form">
        <div class="email-box" >
        <input type="email" name="mail">
        </div>
        <div class="password-box">
        <input type="password" name="password">
        </div>
        </div>
        <div class="buttons">
        <input type="submit" value="Login"> <input type="reset">
        </div>
        </form>
    </div>
       
</body>
</html>