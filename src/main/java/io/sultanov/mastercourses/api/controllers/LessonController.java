package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.LessonDTO;
import io.sultanov.mastercourses.api.mappers.LessonMapper;
import io.sultanov.mastercourses.api.views.LessonView;
import io.sultanov.mastercourses.domain.lessons.Lesson;
import io.sultanov.mastercourses.domain.lessons.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LessonController {
    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @GetMapping(value = "/lessons", produces = "application/json")
    public List<LessonView> getLessons() {
        return lessonMapper.toViews(lessonService.getLessons());
    }

    @PostMapping(value = "/lesson/create", consumes = "application/json", produces = "application/json")
    public Lesson createLesson(@Valid @RequestBody LessonDTO lessonDTO) {
        return lessonService.createLesson(lessonMapper.toLesson(lessonDTO));
    }

    @GetMapping(value = "/lesson/{number}", produces = "application/json")
    public LessonView getLesson(@PathVariable Long number) {
        return lessonMapper.toView(lessonService.getLesson(number));
    }
}
