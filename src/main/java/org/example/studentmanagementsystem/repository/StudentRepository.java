package org.example.studentmanagementsystem.repository;

import org.example.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import  java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final DataSource dataSource;

    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveStudent(Student student) {
        String sql = "Insert into students(full_name,age,email)values(?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String fullName = student.getFirstName() + " " + student.getLastName();

            statement.setString(1, fullName);
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error saving student", e);
        }
    }
        public List<Student> findAllStudents() {
            List<Student> students = new ArrayList<>();

            String sql = "select * from students";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String fullName = resultSet.getString("full_name");
                        int age = resultSet.getInt("age");
                        String email = resultSet.getString("email");
                        String[] names = fullName.split(" ", 2);

                        String firstName = names[0];
                        String lastName = names.length > 1 ? names[1] : "";
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error retrieving students",e);
            }
                return students;
            }
        }



