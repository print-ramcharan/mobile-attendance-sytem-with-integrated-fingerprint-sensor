<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Reset Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #BB86FC; /* Background color to match your theme */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        p {
            text-align: center;
            color: #555;
            max-width: 400px;
        }

        a {
            text-align: center;
            color: #663399; /* Updated link color for better visibility */
            text-decoration: none;
            transition: color 0.3s;
        }

        a:hover {
            color: #551A8B; /* Hover color to match your theme */
        }

        @media screen and (max-width: 768px) {
            body {
                background-color: #BB86FC;
            }
        }

        @media screen and (max-width: 480px) {
            body {
                background-color: #BB86FC;
            }
        }
    </style>
</head>
<body>
    <h1>Password Reset Error</h1>
    <p>We could not find the email address you provided in our records. Please make sure you entered the correct email address or <a href="password-reset-request.jsp">click here to try again</a>.</p>
</body>
</html>
