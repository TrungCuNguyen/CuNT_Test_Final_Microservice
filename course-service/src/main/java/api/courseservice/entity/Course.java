package api.courseservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String courseName;

    @Column
    private Integer capacity;

    @Column
    private Integer credits;
}
