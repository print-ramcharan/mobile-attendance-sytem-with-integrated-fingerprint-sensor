<!DOCTYPE html>
<html>
<head>
    <title>Password Reset Request</title>
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

        .form-container {
            background: #fff; /* White background for the form container */
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        form {
            text-align: center;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input[type="email"] {
            width: 100%; /* Center-align the text input */
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #000; /* Black border for the input */
            border-radius: 4px;
        }

        button {
            background: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background: #0056b3;
        }

        @media screen and (max-width: 768px) {
            body {
                background-color: #BB86FC;
            }

            .form-container {
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }
        }

        @media screen and (max-width: 480px) {
            body {
                background-color: #BB86FC;
            }

            .form-container {
                box-shadow: none;
            }
        }
    </style>
</head>
<body>
    <h1>Password Reset Request</h1>
    <div class="form-container">
        <form action="password-reset" method="get">
            <label for="email">Enter your email:</label>
            <input type="email" id="email" name="email" required>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
