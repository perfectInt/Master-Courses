package io.sultanov.mastercourses.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class LessonDTO {
    @Positive
    @NotNull
    private Long number;

    @NotBlank
    private String lessonName;

    @NotBlank
    private String lessonText;
}
