package com.example.stuadentmanager.controller;

import com.example.stuadentmanager.Model.Classroom;
import com.example.stuadentmanager.Model.Student;
import com.example.stuadentmanager.StudentDao.ClassroomDAO;
import com.example.stuadentmanager.StudentDao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(request,response);
                break;
            case "delete":
                try {
                    deleteStudent(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                update(request,response);
                break;
            default:
                try {
                    displayStudent(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createStudent(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                updateForm(request,response);
                break;
            case "search":
                try {
                    search(request,response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    displayStudent(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

        }
    }
    private void displayStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO();
        RequestDispatcher rd = request.getRequestDispatcher("displayStudent.jsp");
        request.setAttribute("studentList", studentDAO.getStudentList());
        rd.forward(request, response);
    }
    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        Classroom classroom = classroomDAO.findClassroomById(Integer.parseInt(request.getParameter("class")));
        Student student = new Student(id,name,birth,address,phone,email,classroom);
        studentDAO.updateStudent(student);
        response.sendRedirect("students");

    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        RequestDispatcher rd = request.getRequestDispatcher("updateStudent.jsp");
        request.setAttribute("listClass",classroomDAO.getClassroomList());
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("student",studentDAO.findStudent(id));
        rd.forward(request, response);
    }
    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        ClassroomDAO classroomDAO = new ClassroomDAO();
        Classroom classroom = classroomDAO.findClassroomById(Integer.parseInt(request.getParameter("class")));
        Student student = new Student(name,birth,address,phone,email,classroom);
        if (student!=null){
        studentDAO.createStudent(student);
            response.sendRedirect("students");}

        else {
            response.sendRedirect("students");
        }


    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassroomDAO classroomDAO = new ClassroomDAO();
        request.setAttribute("listClass",classroomDAO.getClassroomList());
        request.getRequestDispatcher("createStudent.jsp").forward(request,response);
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        StudentDAO studentDAO = new StudentDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delStudent(id);
        response.sendRedirect("/students");
    }
private  void search(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("search");
        StudentDAO studentDAO = new StudentDAO();
    List<Student> list = studentDAO.search(name);
    if (!list.isEmpty()){
        request.setAttribute("studentList", list);
        request.getRequestDispatcher("displayStudent.jsp").forward(request,response);
    }else {
        response.sendRedirect("students");
    }

}
}
