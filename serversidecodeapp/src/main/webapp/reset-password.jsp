<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
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

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            background-color: #FFFFFF; /* White background */
            border-radius: 10px;
            padding: 20px;
            max-width: 400px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin: 10px 0;
            color: #555;
        }

        input[type="password"] {
            width: 100%;
            padding: 10px;
            padding-right:10px;
            border: 1px solid #BB86FC; /* Border color to match your theme */
            border-radius: 5px;
            margin: 10px 0;
        }

        input[type="submit"] {
            background-color: #BB86FC; /* Button color to match your theme */
            color: #FFFFFF;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #663399; /* Hover color to match your theme */
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
    <h2>Reset Your Password</h2>
    <form action="reset-password" method="get">
        <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <br>
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>
