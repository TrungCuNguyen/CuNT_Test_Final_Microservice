package api.courseservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
    private String courseName;
    private Integer capacity;
    private Integer credits;
}
