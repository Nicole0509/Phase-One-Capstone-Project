package web;

import CRUD_Implemantation.StudentImplementation;
import models.Student;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private Connection connection;
    private Gson gson = new Gson();

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/your_database_name",
                    "your_user",
                    "your_password"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all students
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        StudentImplementation dao = new StudentImplementation(connection);
        List<Student> students = dao.getAllStudents();
        response.getWriter().write(gson.toJson(students));
    }

    // Create new student
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Student s = gson.fromJson(request.getReader(), Student.class);
        StudentImplementation dao = new StudentImplementation(connection);
        dao.createStudentFromObject(s);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    // Update a student (PUT)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Parse JSON body
        Student s = gson.fromJson(request.getReader(), Student.class);
        int id = s.getId();

        if (id <= 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid ID");
            return;
        }

        StudentImplementation dao = new StudentImplementation(connection);
        String result = dao.update(
                id,
                s.getNames(),
                s.getEmail(),
                s.getPhoneNumber(),
                s.getDateOfBirth(),
                s.getAddress()
        );

        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + result + "\"}");
    }
    
    // Delete student by id
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentImplementation dao = new StudentImplementation(connection);
        dao.delete(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
