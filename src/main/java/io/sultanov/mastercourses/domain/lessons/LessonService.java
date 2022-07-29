package io.sultanov.mastercourses.domain.lessons;

import io.sultanov.mastercourses.exceptions.lessons.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> getLessons() {
        return lessonRepository.findByOrderByNumber();
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson getLesson(Long id) {
        return lessonRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Lesson editLesson(Long id, Lesson lesson) {
        Lesson lessonFromDb = lessonRepository.findById(id).orElseThrow(LessonNotFoundException::new);
        lessonFromDb.setTitle(lesson.getTitle());
        lessonFromDb.setDescription(lesson.getDescription());
        lessonFromDb.setNumber(lesson.getNumber());
        return lessonFromDb;
    }

    public ResponseEntity<?> deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(LessonNotFoundException::new);
        lessonRepository.delete(lesson);
        return ResponseEntity.ok(Map.of(
                "status", "succesful!"
        ));
    }
}