package com.wellness;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// needed for hashing passwords
import java.security.MessageDigest;

public class DBUtil {

    public void connectDB(){
        DBConnect db = new DBConnect();
        db.conn();
    }

    public boolean ValidAcc(){
        return true; // DB Checks to add here
    }

    public boolean CheckPassword(String SID, String Password) throws SQLException {
        DBConnect db = new DBConnect();
        int SIDParsed = tryParseInt(SID, 0);
        try {
            PreparedStatement st = db.conn().prepareStatement("SELECT \"Password\" FROM users.userstable WHERE \"Student_Number\" = ?");
            st.setInt(1, SIDParsed);

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                if (rs.getString(1).equals(Password)){
                    System.out.println("Correct Password");
                    return true;
                }
            }
            rs.close();
            st.close();
        } catch (Exception e){
            System.out.println("Error getting name: "+e.getMessage());
        }

        System.out.println("incorrect Password");
        return false;
    }

    public String getName(String Student_Number){
        DBConnect db = new DBConnect();
        int StudentNrParsed = tryParseInt(Student_Number, 0);
        String name = "", surname = "";

        try{
            PreparedStatement st = db.conn().prepareStatement(
                    "SELECT \"Name\", \"Surname\" FROM users.userstable WHERE \"Student_Number\" = ?");
            st.setInt(1, StudentNrParsed);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    surname = rs.getString("surname");
                }
            }
        }
        catch (Exception e){
            System.out.println("Error getting name: "+e.getMessage());
        }


        return name + " " + surname;
    }




    public int tryParseInt(String Val, int defaultVal){
        try{
            return Integer.parseInt(Val);
        }
        catch (NumberFormatException e){
            return defaultVal;
        }
    }

    // Need to hash passwords used in Register and login
    public static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
