package com.example.stuadentmanager.StudentDao;

import com.example.stuadentmanager.Model.Classroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    String jdbcURL = "jdbc:mysql://localhost:3306/student_manager";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    public List<Classroom> getClassroomList(){
        List<Classroom> list = new ArrayList<>();
        String query = "SELECT * FROM classroom;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Classroom(rs.getInt(1),rs.getString(2)));
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public Classroom findClassroomById(int id){
        String query = "SELECT * FROM classroom where id=?;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Classroom(rs.getInt(1),rs.getString(2));
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
