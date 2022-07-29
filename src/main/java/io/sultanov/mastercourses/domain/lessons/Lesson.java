package io.sultanov.mastercourses.domain.lessons;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "lessons")
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_lesson")
    private Long number;

    @Column(name = "name_of_lesson")
    private String title;

    @Column(name = "text_of_lesson")
    private String description;

    @Column(name = "visibility")
    private Boolean visible;
}
