package com.example.question2_student_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        
        students.add(new Student(1L, "Alice", "Muhoza", "alicemuhoz1@gmail.com", "Mobile Programming", 3.8));
        students.add(new Student(2L, "Bob", "Mucyo", "bobmucyo2@gmail.com", "Web Technology", 3.2));
        students.add(new Student(3L, "David", "Manzi", "davidmanzi3@gmail.com", "Operating Systems", 3.9));
        students.add(new Student(4L, "Emmanuel", "Nsabimana", "emmanuel4@gmail.com", "Business", 3.4));
        students.add(new Student(5L, "John", "Kamanzi", "johnkamanzi5@gmail.com", "Java Programming", 3.6));
    }

   
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

   
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                result.add(s);
            }
        }
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= gpa) {
                result.add(s);
            }
        }
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(201).body(student);
    }

    
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                s.setFirstName(updatedStudent.getFirstName());
                s.setLastName(updatedStudent.getLastName());
                s.setEmail(updatedStudent.getEmail());
                s.setMajor(updatedStudent.getMajor());
                s.setGpa(updatedStudent.getGpa());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
