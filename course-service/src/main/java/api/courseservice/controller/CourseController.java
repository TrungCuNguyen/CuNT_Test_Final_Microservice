package api.courseservice.controller;

import api.courseservice.dto.CourseRequest;
import api.courseservice.dto.CourseResponse;
import api.courseservice.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById (@PathVariable Long courseId) {
        CourseResponse courseResponse = courseService.getCourseById(courseId);
        return ResponseEntity.ok(courseResponse);
    }


    @GetMapping()
    public ResponseEntity<List<CourseResponse>> getAllCourses () {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequest));
    }

    @PutMapping("/{courseId}/decrease-capacity")
    public ResponseEntity<CourseResponse> decreaseCapacity(@PathVariable Long courseId) {
        return ResponseEntity.ok().body(courseService.decreaseCapacity(courseId));
    }
}
