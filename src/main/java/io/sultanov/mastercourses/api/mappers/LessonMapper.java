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
        lessonView.setLessonName(lesson.getLessonName());
        lessonView.setLessonText(lesson.getLessonText());
        return lessonView;
    }

    public List<LessonView> toViews(List<Lesson> lessons) {
        return lessons.stream().map(this::toView).toList();
    }

    public Lesson toLesson(LessonDTO lessonDTO) {
        return new Lesson()
                .setNumber(lessonDTO.getNumber())
                .setLessonName(lessonDTO.getLessonName())
                .setLessonText(lessonDTO.getLessonText());
    }
}