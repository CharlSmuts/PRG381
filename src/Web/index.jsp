<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-button {
            padding: 8px 16px;
            background-color: crimson;
            color: white;
            border: none;
            cursor: pointer;
        }

        .register-button {
                    padding: 8px 16px;
                    background-color: crimson;
                    color: white;
                    border: none;
                    cursor: pointer;
                }


        .login-button:hover {
            background-color: darkred;
        }

        .register-button:hover {
            background-color: darkred;
        }


        div {
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 600px;
            width: 90%;
        }
    </style>
</head>
<body>

    <div>
        <h1>Welcome to the Student Wellness Portal</h1>


            <a href="login.jsp"><button class="login-button" type="submit">Login</button></a>
            <p></p>
            <a href="register.jsp"><button class="register-button" type="submit">Register</button></a>


    </div>

</body>
</html>