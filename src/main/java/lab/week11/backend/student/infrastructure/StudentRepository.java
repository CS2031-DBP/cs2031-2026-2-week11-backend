package lab.week11.backend.student.infrastructure;

import lab.week11.backend.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
