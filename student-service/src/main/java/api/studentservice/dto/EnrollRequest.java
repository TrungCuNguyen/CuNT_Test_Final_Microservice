package api.studentservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollRequest {
    private Long studentId;
    private Long courseId;
}
