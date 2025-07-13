package com.wellness;

// needed for the web stuff
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

// needed for database stuff
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterServlet extends HttpServlet {

    private boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // check that name contains only letters and spaces (sorry Elon musk's son)
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            return false;
        }
        // check that surname contains only letters and spaces
        for (int i = 0; i < surname.length(); i++) {
            char c = surname.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    // needed for the email validation tests for the @ and . what ever
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        int atPos = email.indexOf('@');
        int dotPos = email.lastIndexOf('.');
        return atPos > 0 && dotPos > atPos;
    }

    // needed to test if the phone number entered is correct
    private boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // it gets real annoying real fast having to reinput the form fields after each input error
    private void saveInput(HttpSession session, String name, String surname, String email, String phone) {
        session.setAttribute("name", name);
        session.setAttribute("surname", surname);
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // needed for sending conformation or error messages to the jsp and keeping the form input after invalid input
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Validate basic fields
        if (name == null || surname == null || email == null || password == null ||
                name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            session.setAttribute("msg", "All fields are required");
            response.resetBuffer();
            response.sendRedirect("register.jsp");
            saveInput(session, name, surname, email, phone);
            return;
        }

        if (!isValidName(name)) {
            session.setAttribute("msg", "Invalid first name");
            response.sendRedirect("register.jsp");
            saveInput(session, name, surname, email, phone);
            return;
        }

        if (!isValidSurname(surname)) {
            session.setAttribute("msg", "Invalid surname");
            response.sendRedirect("register.jsp");
            saveInput(session, name, surname, email, phone);
            return;
        }

        if (!isValidEmail(email))
        {
            session.setAttribute("msg", "Email format is incorrect");
            response.sendRedirect("register.jsp");
            saveInput(session, name, surname, email, phone);
            return;
        }

        if (!isValidPhone(phone)) {
            session.setAttribute("msg", "Phone number format is incorrect");
            response.sendRedirect("register.jsp");
            saveInput(session, name, surname, email, phone);
            return;
        }

        try {
            DBConnect db = new DBConnect();
            Connection conn = db.conn();

            // Check if user with same email or phone already exists
            String checkQuery = "SELECT 1 FROM users.userstable WHERE \"Email\" = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                rs.close();
                checkStmt.close();
                conn.close();
                session.setAttribute("msg", "User with same email");
                response.resetBuffer();
                response.sendRedirect("register.jsp");
                saveInput(session, name, surname, email, phone);
                return;
            }
            rs.close();
            checkStmt.close();

            // Generate next student number manually (The nuklear option if this was used in real live environment)
            int newStudentNumber = 1; // Default if no records exist
            String maxQuery = "SELECT MAX(\"Student_Number\") FROM users.userstable";
            PreparedStatement maxStmt = conn.prepareStatement(maxQuery);
            ResultSet maxRs = maxStmt.executeQuery();

            if (maxRs.next()) {
                newStudentNumber = maxRs.getInt(1) + 1;
            }

            maxRs.close();
            maxStmt.close();

            // Insert new user with generated student number (The case must be exact ex: upper case Name escaped quotes)
            String insertQuery = "INSERT INTO users.userstable (\"Student_Number\", \"Name\", \"Surname\", \"Email\", \"Phone\", \"Password\") VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, newStudentNumber); // made on the fly: see above
            insertStmt.setString(2, name);
            insertStmt.setString(3, surname);
            insertStmt.setString(4, email);
            insertStmt.setString(5, phone);
            String hashedPassword = DBUtil.hashPassword(password); // hashing the password
            insertStmt.setString(6, hashedPassword);

            insertStmt.executeUpdate();
            insertStmt.close();
            conn.close();

            session.setAttribute("msg", "Registration successful. Your student number is " + newStudentNumber);
            response.resetBuffer();
            response.sendRedirect("register.jsp");

            session.removeAttribute("name");
            session.removeAttribute("surname");
            session.removeAttribute("email");
            session.removeAttribute("phone");
        } catch (Exception e) {
            e.printStackTrace();
//            response.sendRedirect("register.jsp?msg=Error: " + e.getMessage()); // for us to see not the end user
        }
    }
}
