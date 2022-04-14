package rifqimuhammadaziz.service;

import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.util.DaoService;
import rifqimuhammadaziz.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DaoService<Department> {

    @Override
    public List<Department> getAll() throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        String QUERY = "SELECT id, name FROM department";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) { // result from query
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        departments.add(department);
                    }
                }
            }
        }
        return departments;
    }

    @Override
    public Department findById(Integer id) throws SQLException, ClassNotFoundException {
        Department department = null;
        String QUERY = "SELECT id, name FROM department WHERE id=?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) { // result from query
                    while (rs.next()) {
                        department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                    }
                }
            }
        }
        return department;
    }

    @Override
    public List<Department> findByName(String name) throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        String QUERY = "SELECT * FROM department WHERE name = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        departments.add(department);
                    }
                }
            }
        }
        return departments;
    }


    @Override
    public int addData(Department department) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "INSERT INTO department(name) VALUES(?)";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, department.getName());

                // executeUpdate for string query (insert, update, delete)
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
    public int updateData(Department department) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "UPDATE department SET name = ? WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, department.getName());
                ps.setInt(2, department.getId());
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
    public int deleteData(Department department) throws SQLException, ClassNotFoundException {
        int result = 0;
        String QUERY = "DELETE FROM department WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, department.getId());
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

    // Get Single Data
//    public Department findByName(String departmentName) throws SQLException, ClassNotFoundException {
//        Department department = null;
//        String QUERY = "SELECT id, name FROM department WHERE name=?";
//        try (Connection connection = MySQLConnection.createConnection()) {
//            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
//                ps.setString(1, departmentName);
//                try (ResultSet rs = ps.executeQuery()) { // result from query
//                    while (rs.next()) {
//                        department = new Department();
//                        department.setId(rs.getInt("id"));
//                        department.setName(rs.getString("name"));
//                    }
//                }
//            }
//        }
//        return department;
//    }
}
