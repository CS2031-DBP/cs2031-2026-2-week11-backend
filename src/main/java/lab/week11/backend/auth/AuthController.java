package lab.week11.backend.auth;

import lab.week11.backend.student.domain.Student;
import lab.week11.backend.student.domain.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        Student student = new Student();
        student.setFirstname(request.firstname());
        student.setLastname(request.lastname());
        student.setEmail(request.email());
        student.setPhone(request.phone());
        student.setAge(request.age());
        student.setDescription(request.description());
        student.setPassword(request.password());

        studentService.createUser(student);

        return ResponseEntity.ok(new AuthResponse("registered-" + request.email()));
    }
}
