<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page session="true" %>
<%
    HttpSession currentSession = request.getSession(false); // Dont create a session if none exists

    // If the session is not valid, redirect
    if (currentSession == null || currentSession.getAttribute("studentNumber") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String studentName = (String) currentSession.getAttribute("studentName");
    String studentNumber = (String) currentSession.getAttribute("studentNumber");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        .logout-button {
            padding: 8px 16px;
            background-color: crimson;
            color: white;
            border: none;
            cursor: pointer;
        }
        .logout-button:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>

    <h1>Welcome, <%= studentName %> (<%= studentNumber %>)</h1>

    <form action="LogoutServlet" method="post">
        <button class="logout-button" type="submit">Logout</button>
    </form>

</body>
</html>
