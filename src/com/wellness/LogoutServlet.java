package com.wellness;
import com.wellness.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBUtil dbU = new DBUtil();
        // Invalidate the session
        HttpSession session = request.getSession(!dbU.ValidAcc());
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
