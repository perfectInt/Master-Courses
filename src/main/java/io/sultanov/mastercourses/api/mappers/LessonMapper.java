package io.sultanov.mastercourses.api.mappers;

import io.sultanov.mastercourses.api.dtos.LessonDTO;
import io.sultanov.mastercourses.api.views.LessonView;
import io.sultanov.mastercourses.domain.lessons.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonMapper {

    public LessonView toView(Lesson lesson) {
        LessonView lessonView = new LessonView();
        lessonView.setNumber(lesson.getNumber());
        lessonView.setDescription(lesson.getDescription());
        lessonView.setTitle(lesson.getTitle());
        return lessonView;
    }

    public List<LessonView> toViews(List<Lesson> lessons) {
        return lessons.stream().map(this::toView).toList();
    }

    public Lesson toLesson(LessonDTO lessonDTO) {
        return new Lesson()
                .setNumber(lessonDTO.getNumber())
                .setDescription(lessonDTO.getDescription())
                .setTitle(lessonDTO.getTitle());
    }
}