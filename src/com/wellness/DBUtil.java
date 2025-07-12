package com.wellness;

import lib.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        System.out.println("incorrect Password");
    return false;
    }

    public String getName(){
        return "tempName tempSurname";
    }




    public int tryParseInt(String Val, int defaultVal){
        try{
            return Integer.parseInt(Val);
        }
        catch (NumberFormatException e){
            return defaultVal;
        }
    }

}
