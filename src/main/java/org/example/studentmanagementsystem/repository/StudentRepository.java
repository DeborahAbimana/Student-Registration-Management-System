package org.example.studentmanagementsystem.repository;

import org.example.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import  java.sql.SQLException;

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
}

