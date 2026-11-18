package api.studentservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    private String courseName;
    private Integer capacity;
    private Integer credits;
}
