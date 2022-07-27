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
@Builder(toBuilder = true)
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_lesson")
    private Long number;

    @Column(name = "name_of_lesson")
    private String lessonName;

    @Column(name = "text_of_lesson")
    private String lessonText;
}
