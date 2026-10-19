package lab.week11.backend.student.domain;

import jakarta.persistence.*;
import lab.week11.backend.course.domain.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
    private String description;
    private String password;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    public Student() {}

    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Integer getAge() { return age; }
    public String getDescription() { return description; }
    public String getPassword() { return password; }
    public Set<Course> getCourses() { return courses; }

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAge(Integer age) { this.age = age; }
    public void setDescription(String description) { this.description = description; }
    public void setPassword(String password) { this.password = password; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}
