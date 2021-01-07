package server;

import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {
    private Connection conn;

    public StudentDao() {
        try{
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            String dbUrl = "jdbc:mysql://localhost:3306/ltm";
            String username = "admin";
            String password = "A29I^BeEIcck@g";
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> findStudentByName(String name){
        ArrayList<Student> result = new ArrayList();
        String query = "SELECT * FROM ltm.student WHERE name LIKE ?";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birth_year"),
                        rs.getString("class_year"),
                        rs.getString("address")
                );
                result.add(s);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return result;
    }

    public Boolean updateStudent(Student student){
        String query = "UPDATE ltm.student SET name = ?, birth_year = ?, class_year = ?, address = ? WHERE id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setDate(2, student.getBirthYear());
            ps.setString(3, student.getClassYear());
            ps.setString(4, student.getAddress());
            ps.setInt(5, student.getId());

            int row = ps.executeUpdate();
            if(row > 0) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
