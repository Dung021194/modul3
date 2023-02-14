package com.example.stuadentmanager.StudentDao;

import com.example.stuadentmanager.Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    String jdbcURL = "jdbc:mysql://localhost:3306/student_manager";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";
    public List<Student> getStudentList() throws ClassNotFoundException {
        ClassroomDAO classroomDAO = new ClassroomDAO();
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM student";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(  Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)) {

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)
                        ,classroomDAO.findClassroomById(rs.getInt(7))));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public void createStudent(Student student) throws ClassNotFoundException {
        String query = "insert into student(name,date_of_birth,address,phone_number," +
                "email,classroom_id) values(?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        try( Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,student.getName());
            ps.setString(2,student.getDateOfBirth());
            ps.setString(3,student.getAddress());
            ps.setString(4,student.getPhoneNumber());
            ps.setString(5,student.getEmail());
            ps.setInt(6,student.getClassroom().getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void updateStudent(Student student){
        String query = "update student set name=?, date_of_birth = ?,address = ?,phone_number=?,email=?,classroom_id = ? where id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,student.getName());
            ps.setString(2,student.getDateOfBirth());
            ps.setString(3,student.getAddress());
            ps.setString(4,student.getPhoneNumber());
            ps.setString(5,student.getEmail());
            ps.setInt(6,student.getClassroom().getId());
            ps.setInt(7,student.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }
public void delStudent(int id) throws ClassNotFoundException {
    String query = "delete from student where id = ?";
    Class.forName("com.mysql.jdbc.Driver");
    try(Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);) {

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }

}
public Student findStudent(int id){
        ClassroomDAO classroomDAO = new ClassroomDAO();
    String query = "SELECT * FROM student where id=?;";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return new Student(rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6)
                    ,classroomDAO.findClassroomById(rs.getInt(7)));
        }
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    return null;
}
    public List<Student> search(String name) throws ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> studentList = studentDAO.getStudentList();
        List<Student> list =new ArrayList<>();
        for (Student s:studentList
        ) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())){
                list.add(s);
            }

        }
        return list;

    }
}
