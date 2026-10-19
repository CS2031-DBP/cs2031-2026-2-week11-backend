package lab.week11.backend.student.application;

import lab.week11.backend.course.domain.Course;
import lab.week11.backend.student.domain.Student;
import lab.week11.backend.student.domain.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllUsers() {
        return ResponseEntity.ok(studentService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Student> createUser(@RequestBody Student student) {
        Student created = studentService.createUser(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateUser(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateUser(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        studentService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<Course>> getStudentCourses(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentCourses(id));
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.enrollStudentInCourse(studentId, courseId));
    }

    @DeleteMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> unenrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.unenrollStudentFromCourse(studentId, courseId));
    }
}
