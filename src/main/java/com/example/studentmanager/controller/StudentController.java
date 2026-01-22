package com.example.studentmanager.controller;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.studentmanager.service.StudentService;
import java.util.List;
import java.util.Optional;
import com.example.studentmanager.model.Student;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    // READ - Hiển thị danh sách sinh viên
    @GetMapping("/students")
    public String listStudents(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAllStudents();
        }
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // READ - Hiển thị form thêm sinh viên mới
    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student"; // add-student.html
    }

    // CREATE - Thêm sinh viên mới
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute Student student) {
        try {
            studentService.createStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/students/add?error=true";
        }
    }

    // READ - Hiển thị form chỉnh sửa
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "edit-student"; // edit-student.html
        }
        return "redirect:/students";
    }

    // UPDATE - Cập nhật sinh viên
    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute Student studentDetails) {
        try {
            studentService.updateStudent(id, studentDetails);
            return "redirect:/students";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/students/edit/" + id + "?error=true";
        }
    }

    // DELETE - Xóa sinh viên
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/students";
    }
}
