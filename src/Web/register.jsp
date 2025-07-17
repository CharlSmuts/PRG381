<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Wellness - Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .register-container {
            width: 320px;
            margin: 80px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="password"] {
            width: 94%;
            padding: 8px;
            margin: 8px 0 16px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }
        p.login-link {
            text-align: center;
            margin-top: 20px;
        }
        p.login-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        p.login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Register for Wellness Services</h2>

    <%
        if (session != null) {
            String msg = (String) session.getAttribute("msg");
            if (msg != null) {
    %>
        <div class="error-message"><%= msg %></div>
    <%
                session.removeAttribute("msg"); // Remove to prevent repeated display
            }
        }
    %>

    <form action="RegisterServlet" method="post" novalidate>
        <input type="text" name="name" placeholder="First Name" value="<%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %>"><br>
        <input type="text" name="surname" placeholder="Surname" value="<%= session.getAttribute("surname") != null ? session.getAttribute("surname") : "" %>"><br>
        <input type="email" name="email" placeholder="Email" value="<%= session.getAttribute("email") != null ? session.getAttribute("email") : "" %>"><br>
        <input type="tel" name="phone" placeholder="Phone" value="<%= session.getAttribute("phone") != null ? session.getAttribute("phone") : "" %>"><br>
        <input type="password" name="password" placeholder="Password"><br>
        <input type="submit" value="Register">
    </form>

    <p class="login-link">Already have an account? <a href="login.jsp">Login here</a></p>
</div>

</body>
</html>
