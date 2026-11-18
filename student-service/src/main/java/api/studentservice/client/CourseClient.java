package api.studentservice.client;

import api.studentservice.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="course-service")
public interface CourseClient {
    @GetMapping("/api/courses/{courseId}")
    CourseDto getCourseById(@PathVariable Long courseId);

    @PutMapping("/api/course/{courseId}/decrease-capacity")
    CourseDto decreaseCapacity(@PathVariable Long courseId);
}
