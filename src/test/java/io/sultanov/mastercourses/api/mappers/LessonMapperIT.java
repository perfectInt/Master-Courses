package io.sultanov.mastercourses.api.mappers;

import io.sultanov.mastercourses.api.dtos.LessonDTO;
import io.sultanov.mastercourses.api.views.LessonView;
import io.sultanov.mastercourses.domain.lessons.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LessonMapperIT {
    @Autowired
    protected LessonMapper lessonMapper;

    @Test
    public void toViewTest() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setTitle("First lesson");
        lesson.setDescription("It is a first lesson");
        lesson.setNumber(1L);
        lesson.setVisible(true);
        LessonView lessonView = lessonMapper.toView(lesson);
        assertEquals(lesson.getTitle(), lessonView.getTitle());
        assertEquals(lesson.getDescription(), lessonView.getDescription());
        assertEquals(lesson.getNumber(), lessonView.getNumber());
    }

    @Test
    public void toLessonTest() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Second lesson");
        lessonDTO.setDescription("It is a second lesson");
        lessonDTO.setNumber(2L);
        Lesson lesson = lessonMapper.toLesson(lessonDTO);
        assertEquals(false, lesson.getVisible());
        assertEquals(lessonDTO.getTitle(), lesson.getTitle());
        assertEquals(lessonDTO.getDescription(), lesson.getDescription());
        assertEquals(lessonDTO.getNumber(), lesson.getNumber());
    }

    @Test
    public void toViewsTest() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setTitle("First lesson");
        lesson1.setDescription("It is a first lesson");
        lesson1.setNumber(1L);
        lesson1.setVisible(true);
        Lesson lesson2 = new Lesson();
        lesson2.setId(1L);
        lesson2.setTitle("First lesson");
        lesson2.setDescription("It is a first lesson");
        lesson2.setNumber(1L);
        lesson2.setVisible(true);
        List<LessonView> lessonViews = lessonMapper.toViews(List.of(lesson1, lesson2));
        assertEquals(2L, lessonViews.size());

        assertEquals(lesson1.getTitle(), lessonViews.get(0).getTitle());
        assertEquals(lesson1.getDescription(), lessonViews.get(0).getDescription());
        assertEquals(lesson1.getNumber(), lessonViews.get(0).getNumber());

        assertEquals(lesson2.getTitle(), lessonViews.get(1).getTitle());
        assertEquals(lesson2.getDescription(), lessonViews.get(1).getDescription());
        assertEquals(lesson2.getNumber(), lessonViews.get(1).getNumber());
    }
}
