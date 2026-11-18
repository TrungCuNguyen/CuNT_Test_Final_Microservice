package api.courseservice.service;

import api.courseservice.dto.CourseRequest;
import api.courseservice.dto.CourseResponse;
import api.courseservice.entity.Course;
import api.courseservice.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        return mapToResponse(course);
    }

    public List<CourseResponse> getAllCourse() {
        return courseRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = Course.builder()
                .courseName(courseRequest.getCourseName())
                .capacity(courseRequest.getCapacity())
                .credits(courseRequest.getCredits())
                .build();

        Course courseSaved = courseRepository.save(course);
        return mapToResponse(courseSaved);
    }

    public CourseResponse decreaseCapacity (Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        if(course.getCapacity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No capacity for this course");
        }

        course.setCapacity(course.getCapacity() - 1);

        Course courseSaved = courseRepository.save(course);
        return mapToResponse(courseSaved);
    }

    public CourseResponse mapToResponse(Course course){
        return  CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .capacity(course.getCapacity())
                .credits(course.getCredits())
                .build();
    }
}
