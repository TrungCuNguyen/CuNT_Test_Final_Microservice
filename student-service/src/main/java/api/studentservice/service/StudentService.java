package api.studentservice.service;

import api.studentservice.client.CourseClient;
import api.studentservice.dto.CourseDto;
import api.studentservice.dto.EnrollRequest;
import api.studentservice.dto.StudentResponse;
import api.studentservice.entity.Student;
import api.studentservice.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseClient courseClient;

    public StudentResponse enrollCourse(EnrollRequest enrollRequest) {
        Student student = studentRepository.findById(enrollRequest.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found student"));

        if(student.getEnrolled_courses() == null) {
            student.setEnrolled_courses(new ArrayList<>());
        }

        if(student.getEnrolled_courses().contains(enrollRequest.getCourseId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student enrolled this course already");
        }

        CourseDto course = courseClient.getCourseById(enrollRequest.getCourseId());
        if(course == null || course.getCapacity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course is not exist or not for enroll");
        }

        courseClient.decreaseCapacity(enrollRequest.getCourseId());

        student.getEnrolled_courses().add(enrollRequest.getCourseId());

        Student studentSaved = studentRepository.save(student);
        return StudentResponse.builder()
                .id(studentSaved.getId())
                .name(studentSaved.getName())
                .email(studentSaved.getEmail())
                .build();
    }
}
