package lab.week11.backend.student.domain;

import lab.week11.backend.course.domain.Course;
import lab.week11.backend.course.infrastructure.CourseRepository;
import lab.week11.backend.course.exception.CourseNotFoundException;
import lab.week11.backend.student.exception.NotFoundException;
import lab.week11.backend.student.infrastructure.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public Student getUser(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    public Student createUser(Student student) {
        return studentRepository.save(student);
    }

    public Student updateUser(Long id, Student student) {
        Student toUpdate = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
        toUpdate.setFirstname(student.getFirstname());
        toUpdate.setLastname(student.getLastname());
        toUpdate.setEmail(student.getEmail());
        toUpdate.setPhone(student.getPhone());
        toUpdate.setAge(student.getAge());
        toUpdate.setDescription(student.getDescription());
        toUpdate.setPassword(student.getPassword());
        return studentRepository.save(toUpdate);
    }

    public void deleteUser(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }

    public Set<Course> getStudentCourses(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
        return student.getCourses();
    }

    public Student enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    public Student unenrollStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        student.getCourses().remove(course);
        return studentRepository.save(student);
    }
}
