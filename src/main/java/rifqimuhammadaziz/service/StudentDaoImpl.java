package rifqimuhammadaziz.service;

import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.model.Student;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.util.DaoService;
import rifqimuhammadaziz.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements DaoService<Student> {

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        String QUERY = "SELECT s.id, first_name, last_name, address, department_id, name FROM student s JOIN department d ON s.department_id = d.id";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        Department department = new Department();
                        department.setId(rs.getInt("department_id"));
                        department.setName(rs.getString("name"));

                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setAddress(rs.getString("address"));
                        student.setDepartment(department);
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }

    @Override
    public Student findById(Integer id) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        String QUERY = "SELECT * FROM student s JOIN department d ON s.department_id = d.id WHERE s.id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("department_id"));
                        department.setName(rs.getString("name"));

                        student.setId(rs.getInt("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setAddress(rs.getString("address"));
                        student.setDepartment(department);
                    }
                }
            }
        }
        return student;
    }

    @Override
    public List<Student> findByName(String name) throws SQLException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        String QUERY = "SELECT * FROM student s JOIN department d ON s.department_id = d.id WHERE s.first_name = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("department_id"));
                        department.setName(rs.getString("name"));

                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setAddress(rs.getString("address"));
                        student.setDepartment(department);
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }

    @Override
    public int addData(Student student) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "INSERT INTO student(first_name, last_name, address, department_id) VALUES(?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, student.getFirstName());
                ps.setString(2, student.getLastName());
                ps.setString(3, student.getAddress());
                ps.setInt(4, student.getDepartment().getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Student student) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "UPDATE student SET first_name = ?, last_name = ?, address = ?, department_id = ? WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, student.getFirstName());
                ps.setString(2, student.getLastName());
                ps.setString(3, student.getAddress());
                ps.setInt(4, student.getDepartment().getId());
                ps.setInt(5, student.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Student student) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "DELETE FROM student WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, student.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
