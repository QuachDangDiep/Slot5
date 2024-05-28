package JavaDocument;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/AddressBookDB";
        private static final String JDBC_USER = "root";
        private static final String JDBC_PASSWORD = "password";

        public static List<Student> getAllStudents() {
            List<Student> students = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM Student")) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String dob = resultSet.getString("DOB");

                    Student student = new Student(id, name, address, email, phone, dob);
                    students.add(student);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return students;
        }
    public static boolean studentExists(int studentID) throws SQLException {
        boolean exists = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT 1 FROM Student WHERE ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    exists = resultSet.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return exists;
    }

    public static void insertStudent(Student student) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO Student (ID, name, address, email, phone, DOB) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, student.getId());
                statement.setString(2, student.getName());
                statement.setString(3, student.getAddress());
                statement.setString(4, student.getEmail());
                statement.setString(5, student.getPhone());
                statement.setString(6,student.getDob());
                statement.executeUpdate();
            }
        }
    }
    }


