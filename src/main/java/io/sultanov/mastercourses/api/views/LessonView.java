package io.sultanov.mastercourses.api.views;

import lombok.Data;

@Data
public class LessonView {
    private Long number;

    private String title;

    private String description;
}
