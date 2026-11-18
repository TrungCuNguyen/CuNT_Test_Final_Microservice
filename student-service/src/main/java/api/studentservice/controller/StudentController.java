package api.studentservice.controller;

import api.studentservice.dto.EnrollRequest;
import api.studentservice.dto.StudentResponse;
import api.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<StudentResponse> enrollCourse(@RequestBody EnrollRequest enrollRequest) {
        StudentResponse studentResponse = studentService.enrollCourse(enrollRequest);
        return ResponseEntity.ok(studentResponse);
    }
}
