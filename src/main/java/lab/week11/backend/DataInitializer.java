package lab.week11.backend;

import lab.week11.backend.course.domain.Course;
import lab.week11.backend.course.infrastructure.CourseRepository;
import lab.week11.backend.student.domain.Student;
import lab.week11.backend.student.infrastructure.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(StudentRepository studentRepo, CourseRepository courseRepo) {
        return args -> {
            Course c1 = new Course();
            c1.setName("Desarrollo Basado en Plataformas");
            c1.setDescription("Curso de desarrollo full-stack con Spring Boot y React");
            c1.setCredits(4);
            courseRepo.save(c1);

            Course c2 = new Course();
            c2.setName("Algoritmos y Estructuras de Datos");
            c2.setDescription("Fundamentos de algoritmia y estructuras de datos");
            c2.setCredits(4);
            courseRepo.save(c2);

            Course c3 = new Course();
            c3.setName("Bases de Datos");
            c3.setDescription("Diseño y gestión de bases de datos relacionales");
            c3.setCredits(3);
            courseRepo.save(c3);

            Student s1 = new Student();
            s1.setFirstname("Ana");
            s1.setLastname("García");
            s1.setEmail("ana.garcia@utec.edu.pe");
            s1.setPhone("987654321");
            s1.setAge(20);
            s1.setDescription("Estudiante de ingeniería de software");
            s1.setPassword("pass123");
            s1.getCourses().add(c1);
            s1.getCourses().add(c2);
            studentRepo.save(s1);

            Student s2 = new Student();
            s2.setFirstname("Carlos");
            s2.setLastname("López");
            s2.setEmail("carlos.lopez@utec.edu.pe");
            s2.setPhone("912345678");
            s2.setAge(21);
            s2.setDescription("Estudiante de ciencias de la computación");
            s2.setPassword("pass456");
            s2.getCourses().add(c1);
            s2.getCourses().add(c3);
            studentRepo.save(s2);

            Student s3 = new Student();
            s3.setFirstname("Lucía");
            s3.setLastname("Martínez");
            s3.setEmail("lucia.martinez@utec.edu.pe");
            s3.setPhone("998877665");
            s3.setAge(19);
            s3.setDescription("Estudiante de primer año");
            s3.setPassword("pass789");
            studentRepo.save(s3);
        };
    }
}
