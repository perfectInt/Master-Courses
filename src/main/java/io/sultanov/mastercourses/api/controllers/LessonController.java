package io.sultanov.mastercourses.api.controllers;

import io.sultanov.mastercourses.api.dtos.LessonDTO;
import io.sultanov.mastercourses.api.mappers.LessonMapper;
import io.sultanov.mastercourses.api.views.LessonView;
import io.sultanov.mastercourses.domain.lessons.Lesson;
import io.sultanov.mastercourses.domain.lessons.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/lesson/{id}", produces = "application/json")
    public LessonView getLesson(@PathVariable Long id) {
        return lessonMapper.toView(lessonService.getLesson(id));
    }

    @PutMapping(value = "/lesson/edit/{id}", produces = "application/json", consumes = "application/json")
    public LessonView editLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        return lessonMapper.toView(lessonService.editLesson(id, lessonMapper.toLesson(lessonDTO)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/lesson/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        return lessonService.deleteLesson(id);
    }
}
