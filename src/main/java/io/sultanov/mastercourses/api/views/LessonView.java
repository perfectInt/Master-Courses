package io.sultanov.mastercourses.api.views;

import lombok.Builder;
import lombok.Data;

@Data
public class LessonView {
    private Long number;

    private String lessonName;

    private String lessonText;
}
