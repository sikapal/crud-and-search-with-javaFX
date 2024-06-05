package com.skpcorp.crud.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection con;

    public void getDBConn() {

        synchronized ("") {

            try{
                if (this.getCon() == null || this.getCon().isClosed()) {
                        try{
                                String url = "jdbc:mysql://localhost/student_list";
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                setCon(DriverManager.getConnection(url, "root", ""));
                        }catch(ClassNotFoundException | SQLException e){
                        }
                }else{}
            } catch (SQLException ex) {
            }
        }
    }

    public  Connection getCon() {
        return con;
    }

    public static void setCon(Connection acon) {
       con = acon;
    }
    
    public static void closeCOnnection(){
    try{
    con.close();
    }catch(SQLException e){
    }
    
    }
}
