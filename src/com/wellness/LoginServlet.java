package com.wellness;
import com.wellness.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Student_Number = request.getParameter("studentNumber");
        String Password = request.getParameter("password");
            DBUtil dbU = new DBUtil();

            boolean login = false;
            try {
                login = dbU.CheckPassword(Student_Number, Password);
            }
            catch (Exception e){
                System.out.println("error logging in "+e);
            }
        // session

        if (login)
        {

            HttpSession session = request.getSession(true);
            session.setAttribute("studentNumber", Student_Number);
            session.setAttribute("studentName", dbU.getName());

            response.sendRedirect("dashboard.jsp");
        }
        else
        {
            response.sendRedirect("login.jsp");
        }


    }
}

