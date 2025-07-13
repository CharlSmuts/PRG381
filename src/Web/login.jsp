<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Wellness Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .login-container {
            width: 300px;
            margin: 80px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
        }
        input[type="text"], input[type="password"] {
            width: 94%;
            padding: 8px;
            margin: 10px 0;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
        }
        .error {
            color: red;
            text-align: center;
        }
        p.register-link {
            text-align: center;
            margin-top: 20px;
        }
        p.register-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        p.register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Login</h2>
    
    <form action="LoginServlet" method="post">
        <label>Student Number:</label><br>
        <input type="text" name="studentNumber" required><br>
        
        <label>Password:</label><br>
        <input type="password" name="password" required><br>
        
        <input type="submit" value="Login">
    </form>

    <p class="register-link">Need an account? <a href="register.jsp">Register here</a></p>

    <% String error = request.getParameter("error");
       if (error != null) { %>
        <div class="error">Invalid credentials. Please try again.</div>
    <% } %>
</div>

</body>
</html>
