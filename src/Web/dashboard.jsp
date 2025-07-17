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
            background-color: #f2f2f2;
            margin: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
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
        <h1>Welcome, <%= studentName %> (<%= studentNumber %>)</h1>

        <form action="LogoutServlet" method="post">
            <button class="logout-button" type="submit">Logout</button>
        </form>
    </div>

</body>
</html>
