package com.skpcorp.crud.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppQuery {

    private DBConnection c = new DBConnection();

    public void addStudent(com.skpcorp.crud.model.Student student) {

        try {
            c.getDBConn();
            try (java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into student(firstname,middlename,lastname)values(?,?,?)")) {
                ps.setString(1,student.getFirstname());
                ps.setString(2,student.getMiddlename());
                ps.setString(3,student.getLastname());
                ps.execute();
            }
            DBConnection.closeCOnnection();
        } catch (SQLException e) {
        }
    }
    
    public ObservableList<com.skpcorp.crud.model.Student>getStudentList(){
       ObservableList<com.skpcorp.crud.model.Student> studentList = FXCollections.observableArrayList();
        try {
            String query="select id, firstname,middlename,lastname from student order by id desc";
            c.getDBConn();
            Statement st =c.getCon().createStatement();
            ResultSet rs =st.executeQuery(query);
            com.skpcorp.crud.model.Student s;
            
            while(rs.next()){
         s= new com.skpcorp.crud.model.Student(rs.getInt("id"),rs.getString("firstname"),rs.getString("middlename"),rs.getString("lastname"));
         studentList.add(s);
            
        }
            rs.close();
            st.close();
            DBConnection.closeCOnnection();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
     public void updateStudent(com.skpcorp.crud.model.Student student){
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps =c.getCon().prepareStatement("UPDATE `student` \n"
                    + "SET \n"
                    + "`firstname`= ?, \n"
                    + "`middlename`= ?, \n"
                    + "`lastname`= ? \n"
                    + "WHERE `id`= ? ");
                    
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getMiddlename());
            ps.setString(3, student.getLastname());
            ps.setInt(4, student.getId());
            
            ps.execute();
            ps.close();
            c.closeCOnnection();
            
                    
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
     public void deleteStudent(com.skpcorp.crud.model.Student student){
     
         try {
              c.getDBConn();
            java.sql.PreparedStatement ps =c.getCon().prepareStatement("delete from `student` \n"
                    
                    + "WHERE id= ?; ");
            
              ps.setInt(1, student.getId());
            
            ps.execute();
            ps.close();
            c.closeCOnnection();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
     
     }

}
