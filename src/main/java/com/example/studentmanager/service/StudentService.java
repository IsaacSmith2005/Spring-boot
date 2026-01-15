
package com.example.studentmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.example.studentmanager.repository.StudentRepository;
import com.example.studentmanager.model.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    // READ - Lấy tất cả sinh viên
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // READ - Lấy sinh viên theo ID
    public Optional<Student> getStudentById(int id) {
        return repository.findById(id);
    }

    // CREATE - Thêm sinh viên mới
    public Student createStudent(Student student) {
        // Để ID là 0 để JPA tự động sinh từ database
        try {
            return repository.save(student);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Không thể thêm sinh viên", e);
        }
    }

    // UPDATE - Cập nhật sinh viên
    public Student updateStudent(int id, Student studentDetails) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            if (studentDetails.getName() != null) {
                existingStudent.setName(studentDetails.getName());
            }
            if (studentDetails.getAge() > 0) {
                existingStudent.setAge(studentDetails.getAge());
            }
            if (studentDetails.getEmail() != null) {
                existingStudent.setEmail(studentDetails.getEmail());
            }
            return repository.save(existingStudent);
        }
        return null;
    }

    // DELETE - Xóa sinh viên
    public boolean deleteStudent(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


